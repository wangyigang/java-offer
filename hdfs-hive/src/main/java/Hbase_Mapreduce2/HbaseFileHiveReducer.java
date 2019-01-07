package Hbase_Mapreduce2;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;

//public abstract class TableReducer<KEYIN, VALUEIN, KEYOUT>
public class HbaseFileHiveReducer extends TableReducer<ImmutableBytesWritable, Put, NullWritable> {
    @Override
    protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
        //reduce阶段
        for (Put put : values) {
            context.write(NullWritable.get(), put);
        }
    }
}
