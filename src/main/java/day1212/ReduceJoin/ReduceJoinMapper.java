package day1212.ReduceJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class ReduceJoinMapper extends Mapper<LongWritable, Text, ReduceJoinBean, NullWritable> {
    private String filename = null;
    private ReduceJoinBean reduceJoinBean = new ReduceJoinBean();
    /*
    setup：获取文件名称
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        filename = fileSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strings = value.toString().split("\t");
        if("pd.txt".equals(filename)){ // pd
            reduceJoinBean.setPid(strings[0]);
            reduceJoinBean.setPname(strings[1]);
            reduceJoinBean.setFlag("pd");
            reduceJoinBean.setId("");
            reduceJoinBean.setAmount(0);
        }else{//order.txt
            reduceJoinBean.setId(strings[0]);
            reduceJoinBean.setPid(strings[1]);
            reduceJoinBean.setAmount(Integer.parseInt(strings[2]));
            reduceJoinBean.setPname("");
            reduceJoinBean.setFlag("order");
        }

        context.write(reduceJoinBean, NullWritable.get());
    }
}
