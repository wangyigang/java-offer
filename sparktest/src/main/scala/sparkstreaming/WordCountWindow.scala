package sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WordCountWindow {
  def main(args: Array[String]): Unit = {
    //创建conf
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCountWindow")
    //创建streamingcontext
    val ssc = new StreamingContext(conf, Seconds(5))

    //设置checkpoint--窗口函数需要设置检查点，进行保存数据
    ssc.sparkContext.setCheckpointDir("input")

    //从指定的端口获取数据
    val sockStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop102",8888)
    //获取一行并转化成 word 1
    val tupleStream: DStream[(String, Int)] = sockStream.flatMap(_.split(" ")).map((_,1))
    //聚合统计结果
    val window: DStream[(String, Int)] = tupleStream.reduceByKeyAndWindow((s:Int, i:Int)=> s+i,Seconds(15),Seconds(5))
    //打印结果
    window.print()
    //开始
    ssc.start()
    //等待
    ssc.awaitTermination()
  }
}
