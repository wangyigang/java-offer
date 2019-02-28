package customerOutputFormat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

//输入输出文件类型
public class FilterMapper  extends Mapper<LongWritable, Text,LongWritable, Text> {
    //重写map方法
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //以目前的需求来讲，不需要做任何处理，直接写出去即可
        context.write(key,value); //将每行的内容写出
    }
}
