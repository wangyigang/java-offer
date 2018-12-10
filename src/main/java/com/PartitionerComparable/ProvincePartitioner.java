package com.PartitionerComparable;


import com.WritableComparable.FlowBeanCompare;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/*
实现区内排序

泛型是：
 */

@SuppressWarnings("all")
public class ProvincePartitioner extends Partitioner<FlowBeanCompare, Text> {

    @Override
    public int getPartition(FlowBeanCompare flowBeanCompare, Text text ,int numPartitions) {
        String subPhone = text.toString().substring(0, 3);
        switch (subPhone) {
            case "136":
                return 0;
            case "137":
                return 1;
            case "138":
                return 2;
            case "139":
                return 3;
            default:
                return 4;
        }
    }

}

