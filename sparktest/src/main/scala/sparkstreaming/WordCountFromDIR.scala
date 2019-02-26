package sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 注意需要start()
  * 注意点2： 当前需求中不需要进行扁平化 flatmap
  */
object WordCountFromDIR {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    //设置streamingcontext
    val ssc = new StreamingContext(conf,Seconds(5))

    //设置检查点
    ssc.sparkContext.setCheckpointDir("input")

    //从指定端口采集数据，封装为DStream，但是需要保留每一次统计结果
    val sockSM: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102", 8888)
    //拆分为单词

    val mapDStream: DStream[(String, Int)] = sockSM.map((_,1))

    //注意写明类型
    val resultDStream: DStream[(String, Int)] = mapDStream.updateStateByKey((item: Seq[Int], opt: Option[Int]) => {
      var total: Int = item.sum + opt.getOrElse(0)
      Option(total)
    })
    //打印输出信息
    resultDStream.print()
    //注意开启
    ssc.start()
    //等待
    ssc.awaitTermination()
  }
}

