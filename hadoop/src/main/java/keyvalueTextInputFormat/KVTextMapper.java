package keyvalueTextInputFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


//
public class KVTextMapper extends Mapper<Text, Text,Text, IntWritable> {
    private IntWritable v = new IntWritable(1);

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        //1. 封装对象
        //什么也不需要做, 使用keyvalueTextInputFormat的话就key直接是第一个
        //写出
        context.write(key, v);

    }
}
