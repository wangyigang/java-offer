package customerOutputFormat2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FilterDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //进行设置
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(FilterDriver.class);

        job.setOutputFormatClass(FilterFileOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\input\\website\\website.txt"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\input\\website\\output"));

        boolean b = job.waitForCompletion(true);
        System.out.println(b);
    }
}
