package com.WritableComparable;

import com.day1208.FlowBean;
import com.day1208.FlowMapper;
import com.day1208.FlowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
@SuppressWarnings("all")
public class FlowCompareDriver {
    public static void main(String[] args) {
        //设置job和configuration
        Configuration conf = new Configuration();
        try {

            Job job = Job.getInstance(conf);
            //设置jar路径 --新设置
            job.setJarByClass(FlowCompareDriver.class);
            //设置map和reduce
            job.setMapperClass(FlowCompareMapper.class);
            job.setReducerClass(FlowCompareReducer.class);
            //设置map阶段输出类型
//因为涉及到排序，所以需要将flowbean和text的顺序进行调换,然后在reduce阶段，再反过来，这样，就可以使用排序，又可以保证顺序
            job.setMapOutputKeyClass(FlowBeanCompare.class);
            job.setMapOutputValueClass(Text.class);
            //设置reduce阶段输出类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(FlowBeanCompare.class);
            //设置输入输出参数路径
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
