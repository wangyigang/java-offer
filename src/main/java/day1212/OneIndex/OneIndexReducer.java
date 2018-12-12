package day1212.OneIndex;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class OneIndexReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable v = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum =0; //次数
        for (IntWritable value : values) {
            sum+= value.get();
        }
        v.set(sum);
        context.write(key, v);
    }
}
