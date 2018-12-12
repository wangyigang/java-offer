package day1212.OneIndex;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TwoIndexReducer extends Reducer<Text, Text, Text, Text> {
    private StringBuilder stringBuilder = new StringBuilder();
    private Text valueText = new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        stringBuilder.setLength(0);
        for (Text text : values) {
            stringBuilder.append(text+"\t");
        }
        valueText.set(stringBuilder.toString());
        context.write(key, valueText);
    }
}
