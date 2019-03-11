package kafkatest;

import org.apache.kafka.clients.consumer.*;

import java.util.Arrays;
import java.util.Properties;

/*
    注意： 反序列化string ： org.apache.kafka.common.serialization.StringDeserializer
    注意看报出的错误： caused by...
 */

public class KafkaConsumerTest {
    public static void main(String[] args) {
        Properties props= new Properties();
        //bootstrapserver
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092,hadoop103:9092,hadoop105:9092");
        //消费者组id
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        //是否自动提交offset
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        //提交offset的延时
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        //key反序列化
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //value反序列化
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //创建kafkaconsumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //设定主题
        consumer.subscribe(Arrays.asList("mytopic"));
        //消费消息
        while (true){
            //拉取时间
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("record="+record.value());
                System.out.println("partition="+record.partition());
                System.out.println("offset="+record.offset());
            }
        }

    }
}
