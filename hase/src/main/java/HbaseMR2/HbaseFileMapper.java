package HbaseMR2;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class HbaseFileMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {
    //map阶段
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //map输出
        //获取rowkey
        //按照\t进行切分数据
        String[] split = value.toString().split("\t");

        //获取数据
        String rowkey = split[0];
        String name = split[1];
        String color = split[2];

        byte[] rs = Bytes.toBytes(rowkey);


        Put put = new Put(rs);
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(name),Bytes.toBytes(name));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(color),Bytes.toBytes(color));
        ImmutableBytesWritable k = new ImmutableBytesWritable();
        k.set(rs);
        context.write(k, put);
    }
}
