package day1212.OneIndex;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TwoIndexMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text keyText = new Text();
    private Text valueText = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("--");
        System.out.println(split.length);

        String name = split[0];
        keyText.set(name);
        String s2 = split[1];
        valueText.set(s2);

        context.write(keyText, valueText);
    }
}
