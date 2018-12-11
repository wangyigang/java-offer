package day1210.Combiner;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * mapper阶段
 * keyin:longWriteable
 * keyout:输入数据value
 * keyout:输出数据类型
 * longwritebale:输出的数据value类型
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    Text k = new Text();
    IntWritable val = new IntWritable(1);


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //以wordcount文本为案例--atguigu atguigu
        //1. 获取一行
        String line = value.toString();
        //切割单词
        String[] words = line.split(" ");
        for (String word : words) {
            //返回值的key是Text,value是longWritable
            //每次设置key的具体值类型
            k.set(word);
            context.write(k, val);
        }

    }
}
