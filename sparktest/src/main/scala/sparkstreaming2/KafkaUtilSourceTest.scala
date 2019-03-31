package sparkstreaming2

import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaUtilSourceTest {
  def main(args: Array[String]): Unit = {
    //创建sparkconf
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("KafkaUtilSourceTest")

    //创建streamingCOntext
    val streamingContext = new StreamingContext(conf, Seconds(5))

    /*
      def createStream[K: ClassTag, V: ClassTag, U <: Decoder[_]: ClassTag, T <: Decoder[_]: ClassTag](
      ssc: StreamingContext,
      kafkaParams: Map[String, String],
      topics: Map[String, Int],
      storageLevel: StorageLevel
    )
     */
    val kafkaDStream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream[String, String, StringDecoder, StringDecoder](
      streamingContext,
      Map(
        //        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokers
        "zookeeper.connect" -> "hadoop102:2181,hadoop103:2181,hadoop105:2181",
        ConsumerConfig.GROUP_ID_CONFIG -> "spark",
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer",
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer"

      ),
      Map( //topic
        "ss" -> 2
      ),
      StorageLevel.MEMORY_ONLY //仅内存
    )
    kafkaDStream.foreachRDD()
    val resultRdd: DStream[(String, Int)] = kafkaDStream.map(t=>{(t._2,1)}).reduceByKey(_+_)
    resultRdd.print()
    //启动
    streamingContext.start()
    //等待数据
    streamingContext.awaitTermination();

  }


}

