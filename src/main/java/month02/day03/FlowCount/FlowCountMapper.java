package month02.day03.FlowCount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<LongWritable,Text,Text,FlowBean> {
    private FlowBean v = new FlowBean();
    private Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //切割字段
        String[] split = line.split("\t");

        //封装对象
        //获取手机号码
        String phoneNum = split[1];
        //获取上行流量和下行流量
        long upFlow = Long.parseLong(split[split.length-3]);
        long downFlow = Long.parseLong(split[split.length-2]);

        k.set(phoneNum);
        v.set(upFlow, downFlow);
        //数据写出
        context.write(k, v);
    }
}
