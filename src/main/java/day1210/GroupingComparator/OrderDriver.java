package day1210.GroupingComparator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/*
现在需要求出每一个订单中最贵的商品。

期望输出数据
1	222.8
2	722.4
3	232.8
 */
public class OrderDriver {
    public static void main(String[] args) {
        args = new String[]{"d:/input/groupingComparator.txt", "d:/input/output"};
        //获取conf和job
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);
            //设置job类路径
            job.setJarByClass(OrderDriver.class);
            //设置map和reducer
            job.setMapperClass(OrderMapper.class);
            job.setReducerClass(OrderReducer.class);
            //设置map阶段输出类型
            job.setMapOutputKeyClass(OrderBean.class);
            job.setMapOutputValueClass(NullWritable.class);
            //设置reduce阶段输出类型
            job.setOutputKeyClass(OrderBean.class);
            job.setOutputValueClass(NullWritable.class);
            //设置reduce分组grouping
            job.setGroupingComparatorClass(OrderGroupingComparator.class);

            //设置map输出路径
            FileInputFormat.setInputPaths(job, new Path(args[0]));
            //设置reduce阶段输出路径
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
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
