package sparkstreaming

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.receiver.Receiver

import scala.util.control._

object TestCustomerReceiver {
  def main(args: Array[String]): Unit = {

    //使用自定义接收器采集数据
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val ssc = new StreamingContext(conf, Seconds(5))
    //创建自定义reciever
    val lineStream: ReceiverInputDStream[String] = ssc.receiverStream(new CustomerReceiver("hadoop102",8888))
    //进行单词映射成元祖(word, 1)
    val splitStream: DStream[String] = lineStream.flatMap(_.split(" "))
    val mapStream: DStream[(String, Int)] = splitStream.map((_,1))
    //进行累加
    val wordAndCountStreams: DStream[(String, Int)] = mapStream.reduceByKey(_+_)
    //打印
    wordAndCountStreams.print()
    //启动sparkstreamingContext
    ssc.start()
    //等待
    ssc.awaitTermination()
  }
}
class CustomerReceiver(host: String, port:Int) extends Receiver[String](StorageLevel.MEMORY_ONLY){
  var breakFlat : Boolean = true

  //自定义函数
  def receiver(): Unit ={
    //监听端口
    var socket = new Socket(host,port)
    //获取数据
    val reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"))
    var line = ""
    Breaks.breakable{
      while ((line = reader.readLine()) != null) {
        if ("==".equals(line)) {
          Breaks.break()
        }
        store(line)
      }
    }
    //改变标志位
    breakFlat=false
    socket.close()
    reader.close()
  }

  //起始时
  override def onStart(): Unit = {
    new Thread("sock"){
      override def run(): Unit = {
        while(breakFlat){
          receiver()

          Thread.sleep(1000)
        }
      }
    }.start()
  }
  //结束时
  override def onStop(): Unit = {
    breakFlat= false;
  }
}

