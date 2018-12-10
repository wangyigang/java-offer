package com.PartitionerComparable;

import com.WritableComparable.FlowBeanCompare;
import com.WritableComparable.FlowCompareMapper;
import com.WritableComparable.FlowCompareReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
@SuppressWarnings("all")
public class ProvinceDriver {
    public static void main(String[] args) {
        //设置job和configuration
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);
            //设置driver驱动类
            job.setJarByClass(ProvinceDriver.class);

            // 2.5 设置分区类型
            job.setPartitionerClass(ProvincePartitioner.class);
            //设置job reduceTask个数
            job.setNumReduceTasks(5);
            //设置map的类
            job.setMapperClass(FlowCompareMapper.class);
            job.setReducerClass(FlowCompareReducer.class);
            //设置map输出类型
            job.setMapOutputKeyClass(FlowBeanCompare.class);
            job.setMapOutputValueClass(Text.class);
            //设置reduce阶段输出类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(FlowBeanCompare.class);

            //设置输入输出参数路径
            FileInputFormat.setInputPaths(job, new Path("d:/input"));
            FileOutputFormat.setOutputPath(job, new Path("d:/input/output"));
            //提交job
            boolean b = job.waitForCompletion(true);
            System.out.println(b);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
