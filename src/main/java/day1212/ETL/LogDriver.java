package day1212.ETL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class LogDriver {
    public static void main(String[] args) {
        //获取job和configuration
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);
            //设置driver路径
            job.setJarByClass(LogDriver.class);

            job.setMapperClass(LogMapper.class);
            //设置reduceTask数量
            job.setNumReduceTasks(0);

            //设置job输入
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(NullWritable.class);

            //设置job输入输出路径
            FileInputFormat.setInputPaths(job, new Path("d:/input/web.log"));
            FileOutputFormat.setOutputPath(job, new Path("d:/input/logoutput"));

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
