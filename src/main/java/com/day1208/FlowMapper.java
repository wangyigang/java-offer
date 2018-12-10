package com.day1208;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper  extends Mapper<LongWritable, Text, Text, FlowBean> {
    Text k = new Text();
    FlowBean v = new FlowBean();

    //重写map方法
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        /**
         * 7 	13560436666	120.196.100.99		1116		 954			200
         * id	手机号码		网络ip			   上行流量     下行流量     网络状态码
         */
        //获取一行
        String line = value.toString();
        //以空格切割 获取手机号 上流量 下流量
        String[] strings = line.split("\t");
        //获取手机号，上流量，下流量
        String phone = strings[1];
        String upflow = strings[strings.length-3];//上行流量
        String downflow = strings[strings.length-2]; //下行流量

        //封装对象
        k.set(phone);
        v.set(Long.parseLong(upflow), Long.parseLong(downflow));

        //写出
        context.write(k, v);
    }
}
