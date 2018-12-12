package day1212.TogetherFriend;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TogetherFriendReducer extends Reducer<Text, Text, Text, Text> {
    private StringBuilder sb = new StringBuilder();
    private Text valueText = new Text();
    /*
    <B,A>
     */
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        sb.setLength(0);
        for (Text value : values) {
            sb.append(value);
        }
        valueText.set(sb.toString());
        context.write(key, valueText);
    }
}
