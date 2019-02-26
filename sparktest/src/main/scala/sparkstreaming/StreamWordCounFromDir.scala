package sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  *   步骤一：导入pom.xml依赖--sparkStreaming 2.1.11
  *
  */
object StreamWordCountFromDIr {
  def main(args: Array[String]): Unit = {

    // 创建Spark配置信息
    val streamingConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamingWordCount")

    // 创建流式数据处理上下文对象
    val ssc: StreamingContext = new StreamingContext(streamingConf, Seconds(5))

    // 监控指定的目录，获取新增文件中的数据,封装为DStream
    val fileDStream: DStream[String] = ssc.textFileStream("in")

    // 将获取的一行数据分解为单词
    val wordDStream: DStream[String] = fileDStream.flatMap(_.split(" "))

    // 为了统计方便，将单词转换结构，变成元组数据
    val tupleDStream: DStream[(String, Int)] = wordDStream.map(word=>(word, 1))

    // 聚合统计结果
    val resultDStream: DStream[(String, Int)] = tupleDStream.reduceByKey(_+_)

    // 打印结果
    resultDStream.print()

    // 启动流式处理
    ssc.start()

    // 等待数据
    ssc.awaitTermination()
  }
}

