package com.GroupingComparator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/*
现在需要求出每一个订单中最贵的商品。

期望输出数据
1	222.8
2	722.4
3	232.8
 */
public class GroupingDriver {
    public static void main(String[] args) {
        args = new String[]{"d:/input/groupingComparator.txt", "d:/input/output"};
        //获取conf和job
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);

            //设置job类路径
            job.setJarByClass(GroupingDriver.class);
            //设置
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
