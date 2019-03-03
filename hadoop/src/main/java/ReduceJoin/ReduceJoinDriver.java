package ReduceJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ReduceJoinDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(ReduceJoinDriver.class);

        job.setMapperClass(ReducejoinMapper.class);

        job.setReducerClass(ReducejoinReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(ReduceJoinBean.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(ReduceJoinBean.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\input\\reducejoin"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\input\\reducejoin\\output"));

        boolean b = job.waitForCompletion(true);
        System.out.println(b);
    }
}
