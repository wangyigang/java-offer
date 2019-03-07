package sparkstreaming2

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

//滑动窗口
object WindowOperatorTest {
  def main(args: Array[String]): Unit = {
    //创建sparkconf
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WindowOperatorTest")

    //创建sparkstreamingcontext
    val streamingContext = new StreamingContext(conf, Seconds(3))

    //采集数据，获取数据
    val receiverStream: ReceiverInputDStream[String] = streamingContext.socketTextStream("hadoop102", 9999)

    //接收数据后，对数据进行处理
    val mapStream: DStream[(String, Int)] = receiverStream.flatMap(_.split(" ")).map((_, 1))

    //将接收到的数据聚合成一个窗口
    val windowStream: DStream[(String, Int)] = mapStream.reduceByKeyAndWindow(
      (left: Int, righ: Int) => {
        left + righ
      }, Seconds(6), Seconds(3))

    windowStream.print()

    //启动
    streamingContext.start()
    //等待数据
    streamingContext.awaitTermination()
  }
}

