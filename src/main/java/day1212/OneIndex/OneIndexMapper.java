package day1212.OneIndex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class OneIndexMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private String filename = null;
    private Text keyText = new Text();
    private IntWritable v = new IntWritable(1);
    /*
    获取当前处理文件的名称
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        filename = fileSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行数据
        String[] split = value.toString().split(" ");
        for (String s : split) {
            String name = s+ "--"+filename;
            keyText.set(name);
            context.write(keyText, v);
        }

    }
}
