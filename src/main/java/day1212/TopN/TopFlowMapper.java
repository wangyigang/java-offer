package day1212.TopN;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TopFlowMapper extends Mapper<LongWritable, Text, TopFlowBean, Text> {
    private TopFlowBean topbean = new TopFlowBean();
    private Text valueText = new Text();


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        String phone = split[0];
        topbean.set(Long.parseLong(split[1]),Long.parseLong(split[2]));
        valueText.set(phone);
        context.write(topbean, valueText);
    }
}
