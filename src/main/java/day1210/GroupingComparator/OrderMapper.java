package day1210.GroupingComparator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

    private OrderBean ob = new OrderBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1. 把数据包装为Orderbean
        String[] fields = value.toString().split("\t");
        ob.setOrderId(fields[0]);
        ob.setProductId(fields[1]);
        ob.setPrice(Double.parseDouble(fields[2]));

        //2. 写出去
        context.write(ob, NullWritable.get());
    }
}
