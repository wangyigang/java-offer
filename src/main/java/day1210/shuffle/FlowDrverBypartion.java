package day1210.shuffle;


import day1210.day1208.FlowBean;
import day1210.day1208.FlowMapper;
import day1210.day1208.FlowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
@SuppressWarnings("all")
public class FlowDrverBypartion {
    public static void main(String[] args) {
        //設置job
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);
            //设置driver路径
            job.setJarByClass(FlowDrverBypartion.class);
            //设置map
            job.setMapperClass(FlowMapper.class);
            //设置reducer
            job.setReducerClass(FlowReducer.class);
            //设置partionner 分区
            job.setPartitionerClass(ProvincePartioner.class);
            //设置reducer task;
            job.setNumReduceTasks(5);

            //设置map阶段输出参数类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(FlowBean.class);
            //设置最终输出类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(FlowBean.class);
            //设置输入输出路径
            FileInputFormat.setInputPaths(job, new Path("d:/input"));
            FileOutputFormat.setOutputPath(job, new Path("d:/output"));
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
