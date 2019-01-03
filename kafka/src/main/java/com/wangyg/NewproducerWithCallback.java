package com.wangyg;

import org.apache.kafka.clients.producer.*;

import java.util.Arrays;
import java.util.Properties;

public class NewproducerWithCallback {
    public static void main(String[] args) {
        Properties props = new Properties();
        //设置相关属性
        //设置kafka集群
        props.put("bootstrap.servers", "hadoop102:9092");
        //设置应答级别
        props.put("acks", "all");
        //设置失败重试次数
        props.put("retries", 2);
        //设置批处理大小
        props.put("batch.size", 16384);
        //设置延时时间
        props.put("linger.ms", 1);
        //设置缓冲大小
        props.put("buffer.memory", 33554432);
        //设置key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //设置value的序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //设置分区类设置--自定义分区，value设置为全类名
        //如果设置了自定义分区，而且record中没有指定分区，则自定义分区中的分区会覆盖掉key的影响
        //props.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.wangyg.NewMyPartition");

        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
                Arrays.asList("com.wangyg.MyTimeInterceptor","com.wangyg.CountInterceptor"));

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
           // producer.send(new ProducerRecord<String, String>("first", Integer.toString(i), Integer.toString(i)));
            ProducerRecord<String, String> record = new ProducerRecord<>("first", i + "", i + ":reba");
            producer.send(record, new Callback() {
                //callback回调函数
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    //不为空，获取相关元数据
                    if(metadata!=null){
                        long offset = metadata.offset(); //获取offset偏移量
                        int partition = metadata.partition();//获取分区
                        String topic = metadata.topic();//获取主题
                        System.out.println("topic:"+topic+" partition:"+partition+" offset"+offset);
                    }
                }
            });
        }
        producer.close();


    }
}
