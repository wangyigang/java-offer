package day1211.ReduceJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class ReduceJoinMapper extends Mapper<LongWritable, Text, ReduceJoinBean, NullWritable> {
    private String name;
    private ReduceJoinBean bean = new ReduceJoinBean();


    /*
    当前按照单个文件进行分片，所以需要获取每个分片的信息，在启动之前，setup是每个maptask启动前会执行一次，
    所以可以获取当前分片的任务信息
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //TODO
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        //获取当前处理文件名称
       name = fileSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //根据文件名称判断
        //现先获取信息
        String[] fields = value.toString().split("\t");
        //根据当前处理文件的名称封装对象

        /*
            private String id;
            private String pid;
            private int amount;
            private String pname;
         */
        //所有数据不管有没有从中获取，都要进行设置，否则会空指针异常错误
        if("pd.txt".equals(name)){
            //pid  pname
            bean.setId("");
            bean.setAmount(0);
            bean.setPid(fields[0]);
            bean.setPname(fields[1]);
            bean.setFlag("pd");
        }else{ //order
            //id pid amount
            bean.setId(fields[0]);
            bean.setPid(fields[1]);
            bean.setAmount(Integer.parseInt(fields[2]));
            bean.setFlag("order");
            bean.setPname("");
        }

        //将封装对象写出
        context.write(bean, NullWritable.get());

    }
}
