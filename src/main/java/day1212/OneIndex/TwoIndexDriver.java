package day1212.OneIndex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TwoIndexDriver {
    public static void main(String[] args) {
        //设置job和configuration
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);

            //
            job.setJarByClass(TwoIndexDriver.class);

            job.setMapperClass(TwoIndexMapper.class);
            job.setReducerClass(TwoIndexReducer.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            FileInputFormat.setInputPaths(job, new Path("D:\\input\\onindex\\output"));
            FileOutputFormat.setOutputPath(job, new Path("D:\\input\\onindex\\output/output2"));

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
