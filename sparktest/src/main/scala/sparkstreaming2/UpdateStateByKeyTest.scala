package sparkstreaming2

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}


/*
    注意点： 1. checkpointdir 检查点目录可以不用事前手动创建，试了会自动穿件
            2. 使用updatestatebykey 注意设置检查点目录
            3. 方法updateStateBykey是一个汇总方法和reducebykey结果类似，但流式处理中，会结合以前的数据进行汇总
            4.  updatestateBykey输入类型和输出类型需要注意
 */
object UpdateStateByKeyTest {
  def main(args: Array[String]): Unit = {
    //创建sparkconf
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("UpdateStateByKeyTest")

    //创建sparkstreamingcontext
    val streamingContext = new StreamingContext(conf, Seconds(5))
    //设置checkpoint
    streamingContext.sparkContext.setCheckpointDir("checkDir")

    //收集数据，收集数据的方式还是从指定端口号的方式获取
    val receiverStream: ReceiverInputDStream[String] = streamingContext.socketTextStream("hadoop102",9999)

    //对接收到的数据进行处理
    val mapRDDStream: DStream[(String, Int)] = receiverStream.flatMap(_.split(" ")).map((_,1))

    val resultRDD: DStream[(String, Int)] = mapRDDStream.updateStateByKey((seq: Seq[Int], opt: Option[Int]) => {
      var total = seq.sum + opt.getOrElse(0)
      Option(total)
    })
    //打印输出结果
    resultRDD.print()

    //启动
    streamingContext.start()
    //等待数据
    streamingContext.awaitTermination()


  }
}
