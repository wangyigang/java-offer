package day1212.TogetherFriend;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TogetherFriendMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text keyText = new Text();
    private Text valueText = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(":");
        if(split.length<2){
            return;
        }
        System.out.println(split.length);
        String first = split[0];
        valueText.set(first);

        String second = split[1];
        String[] friends = second.split(",");

        for (String s : friends) {
            keyText.set(s);
            context.write(keyText, valueText);
        }
    }
}
