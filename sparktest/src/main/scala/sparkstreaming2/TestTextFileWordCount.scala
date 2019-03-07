package sparkstreaming2

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

/*
    注意点: 1.hdfs协议： hdfs://hadoop102:9000/path

 */
object TestTextFileWordCount {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("TestTextFileWordCount")

    //创建streamingContext
    val streamingContext = new StreamingContext(conf, Seconds(4))

    //监控接收数据源 --将数据通过命令上传到hdfs上 hadoop fs -put a.tsv /fileStream
    val Dstream: DStream[String] = streamingContext.textFileStream("hdfs://hadoop102:9000/fileStream")
    println(Dstream)

    //将数据进行处理
    val resultRDD: DStream[(String, Int)] = Dstream
      .flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

    //打印查看是否正确
    resultRDD.print()

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
