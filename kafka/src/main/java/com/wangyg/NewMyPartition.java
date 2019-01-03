package com.wangyg;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

//自定义分区进行处理数据
public class NewMyPartition implements Partitioner {
    /**
     * 主要进行管理分区，进行分区设置--需要在properties中进行设置
     * @param topic
     * @param key
     * @param keyBytes
     * @param value
     * @param valueBytes
     * @param cluster
     * @return
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //先设置为1
        return 1;
    }

    //关闭资源
    @Override
    public void close() {

    }
    /*
    获取或设置相关属性
     */
    @Override
    public void configure(Map<String, ?> configs) {

    }
}
