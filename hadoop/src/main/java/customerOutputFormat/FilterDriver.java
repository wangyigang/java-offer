package customerOutputFormat;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/*
    需求：过滤输入的Log日志，包含wang的网址输出到wang.log， 其他输出到other.log中
 */
public class FilterDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取conf
        Configuration conf = new Configuration();
        //获取job
        Job job = Job.getInstance(conf);
        //设置job配置
        job.setMapperClass(FilterMapper.class);
        job.setReducerClass(FilterReducer.class);
        job.setOutputFormatClass(FilterOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\input\\website\\website.txt"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\input\\website\\output"));

        boolean b = job.waitForCompletion(true);
        System.out.println(b);
        System.exit(b ? 0 : 1);
    }
}
