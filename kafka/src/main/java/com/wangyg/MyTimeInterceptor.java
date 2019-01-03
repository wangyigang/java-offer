package com.wangyg;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class MyTimeInterceptor implements ProducerInterceptor<String,String> {

    //序列化前，进行提交
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        //获取record的value值
        String value = record.value();
        long currentTimeMillis = System.currentTimeMillis();
        //topic
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(record.topic(), currentTimeMillis + value);
        System.out.println("------"+record.topic() +":"+ record.key());
        return  producerRecord;

    }
    //消息应答前调用
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }
    //关闭资源时调用
    @Override
    public void close() {

    }
    //获取和设置相关配置信息
    @Override
    public void configure(Map<String, ?> configs) {

    }
}
