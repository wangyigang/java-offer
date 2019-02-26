package sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  *   步骤一：导入pom.xml依赖--sparkStreaming 2.1.11
  *
  */
object StreamWordCountTest {
  def main(args: Array[String]): Unit = {
    //初始化sparkconf
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    //创建sparkstreamingcontext--微批次处理
    val ssc = new StreamingContext(conf, Seconds(3)) //参数说明: 1. conf配置信息 2.duration持续时间3
    //采集数据，封装为Dstream--从指定端口获取数据
    val listenStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",8888)
    //处理数据
    val tupleRDD: DStream[(String, Int)] = listenStream.flatMap(_.split(" ")).map((_,1))
    //统计结果
    val resultRDD: DStream[(String, Int)] = tupleRDD.reduceByKey(_+_)
    //打印结果
    resultRDD.print()

    //启动流式处理
    ssc.start()
    //等待
    ssc.awaitTermination()


  }
}

