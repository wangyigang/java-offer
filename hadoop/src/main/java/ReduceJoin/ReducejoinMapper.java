package ReduceJoin;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

//map 映射，为后面做出准备--
public class ReducejoinMapper extends Mapper<LongWritable, Text, Text, ReduceJoinBean> {
    private String filename;
    private Text k = new Text();
    private ReduceJoinBean v = new ReduceJoinBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //数据1001	01	1   |    03	格力
        //过滤非法数据
        //进行转换
        String[] split = value.toString().split("\t");
        if(filename.startsWith("order")){
            if(split.length <3){
                //非法数据
                return ;
            }
            //获取数据，进行封装
            String id = split[0];
            String pdi = split[1];
            Integer amount = Integer.parseInt(split[2]);
            v.set(id, pdi, amount, "", "order");

            k.set(pdi);


        }else if( filename.startsWith("pd")){
            //过滤非法数据
            if(split.length< 2){
                return ; //过滤非法数据
            }
            //获取封装数据
            String pid = split[0];
            String pname = split[1];
            v.set("", pid, 0, pname, "pid");
            k.set(pid);
        }

        context.write(k, v);
    }

    //setup在每个mapTask任务启动时执行一次
    //获取数据源
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit split = (FileSplit) context.getInputSplit();
        filename = split.getPath().getName();
    }

    //任务关闭是执行一次
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        super.cleanup(context);
    }
}
