package interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/*
记录成功或失败次数的拦截器
将数据发送完毕后，在调用回调函数前，调用此拦截器，
进行记录成功或失败的次数，最后统一打印出来
 */
public class KafkaCountInterceptor implements ProducerInterceptor<String,String> {
    private long successCount=0;
    private long errorCount=0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }
    /*
    调用回调函数前，调用此方法，
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if(metadata!=null){
            successCount++;
        }else{
            errorCount++;
        }
    }

    @Override
    public void close() {
        System.out.println("success:"+successCount);
        System.out.println("error:"+errorCount);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
