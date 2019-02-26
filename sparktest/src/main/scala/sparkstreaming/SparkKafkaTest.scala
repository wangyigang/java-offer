package sparkstreaming


import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


//从kafka中采集数据
object SparkKafkaTest {
  def main(args: Array[String]): Unit = {
    //创建sparkconf并初始化ssc
    var conf = new SparkConf().setMaster("local[*]").setAppName("test")
    //创建streamingContext
    val ssc = new StreamingContext(conf, Seconds(5))

    //通过KafkaUtils工具类，进行操作
    //K--分区
    val kafkaDS: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream[String, String, StringDecoder, StringDecoder](
      ssc,
      Map(
        "zookeeper.connect" -> "hadoop102:2181",
        ConsumerConfig.GROUP_ID_CONFIG -> "spark",
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer",
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer"
      ),
      Map(
        "ss" -> 3
      ),
      StorageLevel.MEMORY_ONLY //导包
    )
    val mapDS: DStream[(String, Int)] = kafkaDS.map(t => {
      (t._2, 1)
    })
    val resultDS: DStream[(String, Int)] = mapDS.reduceByKey(_+_)
    //打印结果
    resultDS.print()
    //启动流式处理
    ssc.start()
    //等待数据
    ssc.awaitTermination()
  }
}

