package Partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
@SuppressWarnings("all")
public class FlowDriver {
    public static void main(String[] args) {
        args = new String[]{"D:\\input\\phone.txt","D:\\output"};

        //设置job和configuration
        Configuration conf = new Configuration();
        try {

            Job job = Job.getInstance(conf);
            //设置jar路径
            job.setJarByClass(FlowDriver.class);
            //设置map和reduce
            job.setMapperClass(FlowMapper.class);
            job.setReducerClass(FlowReducer.class);
            //设置map阶段输出类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(FlowBean.class);
            //设置reduce阶段输出类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(FlowBean.class);
            //设置输入输出参数路径
            FileInputFormat.setInputPaths(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            ////增加自定义数据分区设置和reduceTask数量设置
            job.setPartitionerClass(ProvincePartitioner.class);
            job.setNumReduceTasks(5);

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
