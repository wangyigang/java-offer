package day1212.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
@SuppressWarnings("all")

public class OutputFormatDriver {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);

            job.setOutputFormatClass(MyOutputFormat.class);

            //设置输入输出路径
            FileInputFormat.setInputPaths(job, new Path("d:/input/website.txt"));
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
