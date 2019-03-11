package kafkatest;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 *  kafka拦截器
 */
public class KafkaInterceptor implements ProducerInterceptor<String, String> {
    /**
     * 发送数据前
     * @param record
     * @return
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String value = record.value();
        String newValue = System.currentTimeMillis() + ":" + value;
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(record.topic(), newValue);
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
