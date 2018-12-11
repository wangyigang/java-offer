package day1211.Partition;



import day1210.WritableComparable.FlowBeanCompare;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


/*
以flowbean 进行排序
 */
@SuppressWarnings("all")
public class MyPartion extends Partitioner<FlowBeanCompare, Text> {
    @Override
    public int getPartition(FlowBeanCompare flowBeanCompare, Text text, int numPartitions) {
        String phoneStart = text.toString().substring(0, 3);
        switch(phoneStart){
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
