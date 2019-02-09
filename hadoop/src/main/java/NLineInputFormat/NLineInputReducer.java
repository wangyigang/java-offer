package NLineInputFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class NLineInputReducer extends Reducer<Text, IntWritable,Text, IntWritable > {
    private IntWritable v = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //累加求和
        int sum=0;
        for (IntWritable value : values) {
            sum+= value.get();
        }
        v.set(sum);
        context.write(key,v );
    }
}
