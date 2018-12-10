package com.day1208;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper  extends Mapper<LongWritable, Text, Text, FlowBean> {
    private Text k = new Text();
    private FlowBean v = new FlowBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strings = value.toString().split(" ");
        
        String phone = strings[1];

        String upFlow = strings[strings.length-3];
        String downFlow = strings[strings.length-2];
        v.set(Long.parseLong(upFlow), Long.parseLong(downFlow));
        k.set(phone);
        context.write(k ,v);
    }
}
