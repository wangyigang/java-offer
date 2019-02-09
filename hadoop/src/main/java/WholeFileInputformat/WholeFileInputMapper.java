package WholeFileInputformat;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/*
继承FileInputFormat
    重写isSplitable()方法，返回false 不可切割
    重写cretateRedordReader(),创建自定义的RecordReader对象

改写RedcordReader

Driver驱动设置为sewqunenceFIle
 */
public class WholeFileInputMapper extends Mapper<Text, BytesWritable, Text, BytesWritable> {

    @Override
    protected void map(Text key, BytesWritable value, Context context) throws IOException, InterruptedException {
        context.write(key, value);
    }
}
