package com.Combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/*
前两个参数，map阶段输出的k, v
 */
public class WordCountReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
    IntWritable v = new IntWritable();

    //重写reduce方法
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //atguigu 1  atguigu 1
        //1.进行累加 --快捷键：iter
        int sum=0;
        for (IntWritable value : values) {
            sum+=value.get();
        }
        //设置value的值
        v.set(sum);

        //写出
        context.write(key, v);
    }
}
