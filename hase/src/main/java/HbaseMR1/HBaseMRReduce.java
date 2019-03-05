package HbaseMR1;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;

public class HBaseMRReduce extends TableReducer<ImmutableBytesWritable, Put, NullWritable> {

    //reduce方法
    @Override
    protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
        //迭代器方式
        for (Put put : values) {
            //reduce端直接写出去数据即可
            context.write(NullWritable.get(), put);
        }
    }
}
