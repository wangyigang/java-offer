package interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/*
kafka拦截器：
1. 需要实现producerinterceoptor接口

小需求：在每条数据后面加：时间戳
 */
public class KafkaTimeInterceptor implements ProducerInterceptor<String, String> {

    /*
    onSend：在序列化前和分配分区之前进行拦截
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String value = record.value();//获取record的value
        String newValue = value+":"+System.currentTimeMillis();
        String topic = record.topic();
        //通过topic和处理后的newValue进行发送返回
        return new ProducerRecord<>(topic, newValue);

    }

    /*
    在消息被应答（Acknowledgement）之前或者消息发送失败时调用，优先于用户设定的Callback之前执行
    在执行callback之前进行调用
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }
    /*
    关闭资源的时候进行调用
     */
    @Override
    public void close() {

    }
    /*
    获取或设置相关配置
    可以获取配置
    也可以对配置进行设置
     */
    @Override
    public void configure(Map<String, ?> configs) {

    }
}
