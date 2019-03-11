package kafkatest;

import org.apache.kafka.clients.producer.*;


import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/*
    注意点： producer记得进行关闭
 */
public class KafkaProducerWithCallbackTest {
    public static void main(String[] args) {
        //设置properties,设置的属性配置
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092,hadoop013:9092,hadoop105:9092");
        //ack
        prop.put(ProducerConfig.ACKS_CONFIG, "all");
        //重试次数
        prop.put(ProducerConfig.RETRIES_CONFIG, 2);
        //批处理大小
        prop.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");//16K
        //延迟时间
        prop.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        //buffer缓存大小
        prop.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
        //序列化
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

//        //设置自定义分区
//        prop.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomerPartition.class);

        //添加拦截器
        List<String> list = Arrays.asList("kafkatest.KafkaInterceptor", "kafkatest.CountInterceptor");//具有先后顺序
        prop.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, list);

        Producer<String, String> producer = new KafkaProducer<String, String>(prop);
        for(int i=0; i<5; i++){
            producer.send(new ProducerRecord<>("mytopic", i + ""), new Callback() {
                //回调函数
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(metadata!= null) {
                        long offset = metadata.offset();
                        System.out.println(offset);
                        System.out.println("i 输出完成");
                        System.out.println("partition"+metadata.partition());
                    }else if(exception != null){
                        System.out.println(exception.getMessage());
                    }
                }
            });
        }

        //不进行关闭，就不会输出结果
        producer.close();
    }
}

