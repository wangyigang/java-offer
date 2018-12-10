package com.day1208;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

public class WholeFileDriver {
    public static void main(String[] args) {
        //設置job
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);
            //设置driver路径
            job.setJarByClass(WholeFileDriver.class);
            //设置map
            job.setMapperClass(WholeFileMapper.class);
            //设置reducer
            job.setReducerClass(WholeFileReducer.class);
            //设置inputformat
            job.setInputFormatClass(WholeFileInputformat.class);
            //设置outputformat
            job.setOutputFormatClass(SequenceFileOutputFormat.class);
            //设置map阶段输出参数类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(BytesWritable.class);
            //设置最终输出类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(BytesWritable.class);
            //设置输入输出路径
            FileInputFormat.setInputPaths(job, new Path("d:/input"));
            FileOutputFormat.setOutputPath(job, new Path("d:/output"));
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
