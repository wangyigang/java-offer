package day1210.WritableComparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
@SuppressWarnings("all")
public class FlowCompareMapper extends Mapper<LongWritable, Text, FlowBeanCompare, Text> {
    Text v = new Text();
    FlowBeanCompare k = new FlowBeanCompare();

    //重写map方法
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //以空格切割 获取手机号 上流量 下流量
        String[] strings = line.split("\t");
        //获取手机号，上流量，下流量
        String phone = strings[1];
        String upflow = strings[strings.length-3];//上行流量
        String downflow = strings[strings.length-2]; //下行流量

        //封装对象
        v.set(phone);
        k.set(Long.parseLong(upflow), Long.parseLong(downflow));

        //写出
        context.write( k, v);
    }
}
