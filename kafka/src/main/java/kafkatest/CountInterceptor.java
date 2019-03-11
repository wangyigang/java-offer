package kafkatest;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 *  onSend方法不能反悔Null, 否则会报空指针异常
 */
public class CountInterceptor implements ProducerInterceptor<String, String> {
    private int success=0;
    private int error=0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if(metadata!= null){
            success++;
        }else if(exception != null){
            error++;
        }

    }

    /**
     *  关闭的时候进行调用查看 成功多少次， 失败多少次
     */
    @Override
    public void close() {
        System.out.println("success="+success);
        System.out.println("error="+error);

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
