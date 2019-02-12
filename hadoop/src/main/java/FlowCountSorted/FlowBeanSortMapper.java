package FlowCountSorted;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowBeanSortMapper extends Mapper<LongWritable,Text,FlowBean,Text> {
    private FlowBean k = new FlowBean();
    private Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //原始数据：手机号  上行流量  下行流量  总流量
        String line = value.toString();

        //切割
        String[] split = line.split("\t");

        //获取对应数据
        String phoneNum = split[0];

        long upFlow = Long.parseLong(split[1]);
        long downFlow = Long.parseLong(split[2]);
        long sumFlow = Long.parseLong(split[3]);

        //封装对象
        k.setDownFLow(downFlow);
        k.setUpFlow(upFlow);
        k.setSumFlow(sumFlow);

        v.set(phoneNum);

        context.write(k, v);

    }
}
