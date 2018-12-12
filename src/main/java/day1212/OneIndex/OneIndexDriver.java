package day1212.OneIndex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OneIndexDriver {
    public static void main(String[] args) {
        //job和configuration
        Configuration conf = new Configuration();
        try {
            //获取job
            Job job = Job.getInstance(conf);

            job.setJarByClass(OneIndexDriver.class);

            job.setMapperClass(OneIndexMapper.class);
            job.setReducerClass(OneIndexReducer.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            FileInputFormat.setInputPaths(job, new Path("D:\\input\\onindex"));
            FileOutputFormat.setOutputPath(job, new Path("D:\\input\\onindex\\output"));

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
