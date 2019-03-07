package sparkstreaming2

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.receiver.Receiver

/*
    自定义数据源
 */
object CustomerRecevier {
  def main(args: Array[String]): Unit = {
    //创建sparkconf
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("CustomerRecevier")
    //创建streaming
    val streamingContext = new StreamingContext(conf, Seconds(5))
    //创建自动以数据源类，然后进行接收数据
    val receiver: ReceiverInputDStream[String] = streamingContext.receiverStream(
      new CustomerReceiver("hadoop102",9999))

    receiver.print()
    //接收到数据后，对数据进行处理
    val resultRDD: DStream[(String, Int)] = receiver.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

    resultRDD.print()
    //启动
    streamingContext.start()
    //等待数据
    streamingContext.awaitTermination()

  }
}

//自定义数据源，实现监控某个端口号，获取该端口号内容。
class CustomerReceiver(host:String, port:Int) extends Receiver[String](StorageLevel.MEMORY_ONLY){

  /**
    * 开始时调用
    */
  override def onStart(): Unit = {
    //socket编程--启动一个线程，不断接收数据
    new Thread("Socket Receiver"){
      override def run(): Unit = {
        receive();
      }
    }.start() //启动线程
  }

  /**
    *  结束时调用
    */
  override def onStop(): Unit = {

  }

  def receive(): Unit ={
    var socket = new Socket(host,port)
    //创建IO流
    val reader = new BufferedReader(new InputStreamReader(socket.getInputStream,"UTF-8"))

    var str:String =""
    while( !isStopped() &&(str=reader.readLine())!=null){
      store(str)
    }

    //跳出循环则关闭资源
    reader.close()
    socket.close()

    //重启任务
    restart("restart")
  }
}