package day1211.Partition;

import day1210.WritableComparable.FlowBeanCompare;
import day1210.WritableComparable.FlowCompareMapper;
import day1210.WritableComparable.FlowCompareReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MyDriver  {
    public static void main(String[] args) {
        //设置job 和configuration
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);
            job.setJarByClass(MyDriver.class);
            job.setMapperClass(FlowCompareMapper.class);
            job.setReducerClass(FlowCompareReducer.class);
            job.setMapOutputKeyClass(FlowBeanCompare.class);
            job.setMapOutputValueClass(Text.class);
    //设置partition和reduceTask
            job.setPartitionerClass(MyPartion.class);
            job.setNumReduceTasks(5);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(FlowBeanCompare.class);

            FileInputFormat.setInputPaths(job, new Path("d:/input/phone.txt"));
            FileOutputFormat.setOutputPath(job, new Path("d:/input/output"));

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
