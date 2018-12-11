package day1211.groupingCompare;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {
    private OrderBean odb = new OrderBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fiels = value.toString().split("\t");
        String orderId = fiels[0];
        String prodctId = fiels[1];
        double price = Double.parseDouble(fiels[2]);
        odb.setOrderId(orderId);
        odb.setProductId(prodctId);
        odb.setPrice(price);
        context.write(odb, NullWritable.get());

    }
}
