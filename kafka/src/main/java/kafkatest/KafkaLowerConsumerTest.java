package kafkatest;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.Message;
import kafka.message.MessageAndOffset;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SuppressWarnings("all")
public class KafkaLowerConsumerTest {
    public static void main(String[] args) {
        //broers节点
        ArrayList<String> list = new ArrayList<>();
        list.add("hadoop102");
        list.add("hadoop103");
        list.add("hadoop105");
        //设置主题
        String topic = "mytopic";
        //设置分区
        int partition = 0;
        //设置offset;
        long offset=500;

        //获取leader
        String leader = getLeader(list, topic , partition);
        //获取数据
        getData(leader, topic, partition, offset);

    }

    //获取数据
    private static void getData(String leader, String topic, int partition, long offset) {
        //1.创建SimpleConsumer
        SimpleConsumer consumer = new SimpleConsumer(leader,
                9092,
                1000,
                1024 * 1024,
                "getData");
        //2.发送获取数据的请求
        FetchRequestBuilder builder = new FetchRequestBuilder();
        FetchRequest request = builder.addFetch(topic, partition, offset, 1024 * 1024).build();
        //3.获取响应
        FetchResponse response = consumer.fetch(request);
        //4.解析response
        ByteBufferMessageSet messageAndOffsets = response.messageSet(topic, partition);
        //5.遍历messageAndOffsets
        for (MessageAndOffset messageAndOffset : messageAndOffsets) {
            //获取offset
            long newOffset = messageAndOffset.offset();
            //获取数据
            Message message = messageAndOffset.message();
            ByteBuffer byteBuffer = message.payload();
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes);
            System.out.println("value:" + new String(bytes) + " offset:" + newOffset);
        }
    }
    //获取leader
    private static String getLeader(ArrayList<String> list, String topic, int partition) {
        //创建SimpleConsumer
        for (String host : list) {
            SimpleConsumer consumer = new SimpleConsumer(host, 9092,
                    1000, 1024 * 1024, "getLeader");
            //封装获取leader的请求
            TopicMetadataRequest request = new TopicMetadataRequest(Arrays.asList(topic));
            //发送请求，获取相应
            TopicMetadataResponse metadataResponse = consumer.send(request);
            //解析响应
            List<TopicMetadata> topicMetadata = metadataResponse.topicsMetadata();
            //遍历返回结果
            for (TopicMetadata topicMetadatum : topicMetadata) {
                //解析topicMetadata
                List<PartitionMetadata> partitionMetadata = topicMetadatum.partitionsMetadata();
                //遍历partitionMetadata
                for (PartitionMetadata partitionMetadatum : partitionMetadata) {
                    if(partitionMetadatum.partitionId() == partition){
                        String leader = partitionMetadatum.leader().host();
                        return leader;
                    }
                }

            }


        }
        return null;
    }
}
