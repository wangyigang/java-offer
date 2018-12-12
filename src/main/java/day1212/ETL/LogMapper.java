package day1212.ETL;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/*
去除日志中字段长度小于等于11的日志。
（1）输入数据
（2）期望输出数据
每行字段长度都大于11。

 */
public class LogMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    /*
    处理行数据,判断数据是否大于11个
    还有使用计数器应用
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(" ");
        if(split.length >=11){
            context.getCounter("ETL","true").increment(1);
            context.write(value, NullWritable.get());
        }else{
            context.getCounter("ETL", "false").increment(1);
        }

    }
}
