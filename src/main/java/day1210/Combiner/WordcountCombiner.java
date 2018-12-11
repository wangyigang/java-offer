package day1210.Combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordcountCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable sumValue = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum =0;
        for (IntWritable value : values) {
            sum+= value.get();
        }
        sumValue.set(sum);
        context.write(key, sumValue);
    }
}
