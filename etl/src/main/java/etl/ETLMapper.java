package etl;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ETLMapper extends Mapper<LongWritable,Text, Text, NullWritable> {
    private Text k = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //转为字符串
        String string = value.toString();
        //进行etl数据清洗逻辑
        String etlString = ETLUtil.etlString(string);
        //将数据写出
        if(etlString==null)
            return ;
        k.set(etlString);
        context.write(k,NullWritable.get());
    }
}
