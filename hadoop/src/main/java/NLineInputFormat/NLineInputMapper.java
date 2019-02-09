package NLineInputFormat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class NLineInputMapper extends Mapper<LongWritable,Text,Text,LongWritable> {
    private Text k = new Text();
    private LongWritable v = new LongWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String string = value.toString();
        //切割
        String[] split = string.split(" ");
        //循环写出
        for (String word : split) {
            k.set(word);
            context.write(k, v);
        }

    }
}
