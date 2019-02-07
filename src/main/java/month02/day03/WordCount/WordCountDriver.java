package month02.day03.WordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取job实例
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //设置路径
        job.setJarByClass(WordCountDriver.class);
        //设置mapper
        job.setMapperClass(WordCountMapper.class);
        //设置reducer
        job.setReducerClass(WordCountReducer.class);
        //设置map阶段和reducer阶段的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //提交job等待运行
        boolean b = job.waitForCompletion(true);
        System.out.println(b);
        //三目运算符
        System.exit(b?0:1);
    }
}
