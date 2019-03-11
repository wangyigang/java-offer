package kafkatest;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/*
    高级API
    Producerconfig---生产者API的对应属性，里面有所有属性
 */
public class KafkaProducerTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        //设置kafka集群的地址
        props.put("bootstrap.servers", "hadoop102:9092,hadoop103:9092,hadoop105:9092");
        //kafka应答机制，等待leader和follwer都同步完成后，相应应答
        props.put("acks", "all");
        //重试次数
        props.put("retries", 0);
        //批处理大小--基于数据大小的批处理
        props.put("batch.size", 16384);
        //延迟--基于时间的批处理，超过这个时间也会进行发送数据
        props.put("linger.ms", 1);
        //buffer大小缓冲区内存大小
        props.put("buffer.memory", 33554432);
        //序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //反序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //
        Producer<String, String> producer = new KafkaProducer<>(props);
        //第一种方式：只指定value--轮训方式
//        for (int i = 0; i < 10; i++)
//            producer.send(new ProducerRecord<>("mytopic",Integer.toString(i)));

        //第二种方式：指定key和value--按照key
//        for (int i = 0; i < 10; i++)
//            producer.send(new ProducerRecord<>("mytopic",i+"",Integer.toString(i)));

        //第三种方式，指定partition 和key和value--按照指定分区进行消费
        for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<>("mytopic", 0, i + "", Integer.toString(i)));

        producer.close();

    }
}
