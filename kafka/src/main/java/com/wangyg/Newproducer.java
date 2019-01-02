package com.wangyg;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Newproducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        //设置bootstrap-server:kafka集群
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092");
        //设置应答ack标准--all = -1 集群中传递给leader后，leader在接收到后，传递给consumer ack消息
        props.put("acks", "all");
        //发送失败后，重新发送应答次数
        props.put("retries", 2);
        //batch size: 批处理数量16k
        props.put("batch.size", 16384);
        //linger.ms--类似于tcp中拥塞控制算法，增加少量的延时请求，以减少请求--如果生产者达到给定的延时时间，也会发送给kafka
        //默认是0--即无延迟
        props.put("linger.ms", 1);
        //生产者用于缓冲等待发送到消费者的内存总大小
        props.put("buffer.memory", 33554432);
        //key序列化方法
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //value序列化方法
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //new 一个kafkaProducer的实例
        Producer<String, String> producer = new KafkaProducer<>(props);

        //linux中启动一个消费者
        for (int i = 0; i < 10; i++) {
            //方式一:指定分区后，按照分区进行输出 ==>优先按照分区>key>无任何(轮询)
            ProducerRecord<String, String> record = new ProducerRecord<>("first",0,i+"",i+":reba" );
            //方式二： 有key的话，按照key的hashcode %partition
            //ProducerRecord<String, String> record = new ProducerRecord<>("first", i+"", i + "dilireba");
            //方式三：无指定分区：无指定key, 会进行轮询
            //ProducerRecord<String, String> record = new ProducerRecord<>("first", i + "");
            producer.send(record);
        }
        //将生产者进行关闭
        producer.close();

    }
}
