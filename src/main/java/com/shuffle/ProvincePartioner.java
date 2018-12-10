package com.shuffle;

import com.day1208.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class ProvincePartioner extends Partitioner<Text, FlowBean> {

    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        //根据手机号的前三位进行分区
        //手机号136、137、138、139开头都分别放到一个独立的4个文件中，其他开头的放到一个文件中。
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
