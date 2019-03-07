package sparkstreaming2

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object WrodCountByStreaming {
  def main(args: Array[String]): Unit = {
    //创建conf
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WrodCountByStreaming")
    //创建streamcontext
    val streamcontext = new StreamingContext(conf, Seconds(5))

    //采集数据--从端口采集数据
    //从指定端口采集数据
    val socketStream: ReceiverInputDStream[String] = streamcontext.socketTextStream("hadoop102",9999)

    println(socketStream)

    val resultRDD: DStream[(String, Int)] = socketStream.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

    //打印数据
    resultRDD.print()
    //启动流式处理
    streamcontext.start()
    //等待数据进行处理
    streamcontext.awaitTermination()
  }
}
