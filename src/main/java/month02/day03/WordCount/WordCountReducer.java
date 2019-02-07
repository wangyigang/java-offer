package month02.day03.WordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable,Text, IntWritable >{
    private int sum=0;
    private IntWritable v = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        sum=0;
        for (IntWritable value : values) {
            sum+= value.get();
        }

        //输出
        v.set(sum);
        context.write(key, v);
    }
}
