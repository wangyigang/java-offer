package HbaseMR1;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;

import java.io.IOException;

@SuppressWarnings("all")
public class HbaseMRMapper  extends TableMapper<ImmutableBytesWritable, Put> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        Put put = new Put(key.get());
        //遍历rawcell，然后封装成一个一个put对象，然后写出去
        for (Cell cell : value.rawCells()) {
            //使用cellutil工具类
            byte[] family = CellUtil.cloneFamily(cell);
            byte[] qualifier = CellUtil.cloneQualifier(cell);
            byte[] cloneValue = CellUtil.cloneValue(cell);
            put.addColumn(family, qualifier, cloneValue);
            context.write(key, put);
        }
    }
}
