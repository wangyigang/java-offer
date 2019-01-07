package Hbase_Mapreduce2;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class HBaseHDFSFileMapper extends Mapper<LongWritable,Text,ImmutableBytesWritable,Put> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //map阶段
        //1.转成成string类型，对数据进行切分
        String[] split = value.toString().split("\t");
        //切分后数据格式--第一个：rowkey 第二个：name 第三个color
        if(split.length <3){
            return ;
        }
        String rowkey = split[0];
        String name = split[1];
        String color = split[2];
        //新建一个put对象
        Put put = new Put(Bytes.toBytes(rowkey));
        //添加name列信息
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"),
                Bytes.toBytes(name));
        //添加color列数据
        put.addColumn(Bytes.toBytes("info"),
                Bytes.toBytes("color"),
                Bytes.toBytes(color));

        ImmutableBytesWritable im = new ImmutableBytesWritable();
        im.set(Bytes.toBytes(rowkey));
        context.write(im,put);

    }
}
