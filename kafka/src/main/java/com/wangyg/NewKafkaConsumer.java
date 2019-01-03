package com.wangyg;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class NewKafkaConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        //设置kafka集群相关设置
        props.put("bootstrap.servers", "hadoop102:9092");
        //设置消费者组id
        props.put("group.id", "wangyg");
        //是否自动提交offset
        props.put("enable.auto.commit", "true");
        //自动提交offset的间隔
        props.put("auto.commit.interval.ms", "1000");
        //key的反序列化
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //value的反序列化
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //设置kafka消费者consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //订阅的主题-订阅的主题
        consumer.subscribe(Arrays.asList("first"));
        while (true) {
            //poll从broker中拉取数据
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }

        //KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
    }
}
