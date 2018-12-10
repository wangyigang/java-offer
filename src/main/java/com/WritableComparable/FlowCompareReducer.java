package com.WritableComparable;

import com.day1208.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
@SuppressWarnings("all")
public class FlowCompareReducer extends Reducer<FlowBeanCompare,Text, Text, FlowBeanCompare> {
    private FlowBean sumFlow = new FlowBean();

    @Override
    protected void reduce(FlowBeanCompare key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(value, key);
        }
    }
}
