package day1210.Combiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 *   configration : D:/1.txt  D:/output
 */
public class WordCountDriver {
    //psvm--快捷键生成main方法
    public static void main(String[] args) {
        args = new String[]{"d:/input/hello.txt","d:/input/output"};
        //获取job对象
        Configuration conf = new Configuration();
        Job job ;
        try {
            job= Job.getInstance(conf);
            //设置jar存放路径
            job.setJarByClass(WordCountDriver.class);
            //设置combiner
            /*
            combiner：
            设置conbiner条件：
            conbiner的kv和reducer的kv对应
            好处：进行局部合并，减小网络传输负担
             */
            job.setCombinerClass(WordcountCombiner.class);

            //关联mapper和Reduce类
            job.setMapperClass(WordCountMapper.class);
            job.setReducerClass(WordCountReducer.class);

            //4.设置mapper阶段输出阶段的key v 类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            //5.设置reduce最终输入结果的输入路径和输出路径
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            //6.这是输入路径和输出路径
            FileInputFormat.setInputPaths(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            //7.提交job
            boolean result = job.waitForCompletion(true);
            System.out.println("result:"+result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
