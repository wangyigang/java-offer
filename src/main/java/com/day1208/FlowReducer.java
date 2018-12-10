package com.day1208;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    private FlowBean sumFlow = new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long upSum=0;
        long downSum =0;
        for (FlowBean value : values) {
            upSum += value.getUpFlow();
            downSum+= value.getDownFlkow();

            sumFlow.set(upSum, downSum);
            context.write(key, sumFlow);
        }

    }
}
