package day1211.ReduceJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ReducerJoinDriver {
    public static void main(String[] args) {
        //获取job和configuration
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);
            job.setJarByClass(ReducerJoinDriver.class);
            //设置map
            job.setMapperClass(ReduceJoinMapper.class);
            job.setReducerClass(ReduceJoinReducer.class);

            //设置grouping
            job.setGroupingComparatorClass(ReduceJoinGroupingComparator.class);

            job.setMapOutputKeyClass(ReduceJoinBean.class);
            job.setMapOutputValueClass(NullWritable.class);

            job.setOutputKeyClass(ReduceJoinBean.class);
            job.setOutputValueClass(NullWritable.class);

            FileInputFormat.setInputPaths(job, new Path("d:/input/reducejoin"));
            FileOutputFormat.setOutputPath(job, new Path("d:/input/reducejoin/output"));

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
