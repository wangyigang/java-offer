package kafkatest;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomerPartition implements Partitioner {

    /**
     * 自定义分区的逻辑
     * @param topic
     * @param key
     * @param keyBytes
     * @param value
     * @param valueBytes
     * @param cluster
     * @return 返回值是分区号
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return 1;
    }

    /**
     * 关闭资源
     */
    @Override
    public void close() {

    }

    /**
     * 获取并添加相关属性
     * @param configs
     */
    @Override
    public void configure(Map<String, ?> configs) {

    }
}
