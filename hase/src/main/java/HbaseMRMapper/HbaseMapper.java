package HbaseMRMapper;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;

import java.io.IOException;

//第一个参数类型为row key ==》byte[]数组累心
//put类型，put类型是数据插入的类型，所以输出类型为put
public class HbaseMapper extends TableMapper<ImmutableBytesWritable, Put> { //指定两个参数类型，keyout,valueout

    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        //通过map将数据返回成put
        Put put = new Put(key.get());
        //遍历rauCells封装成put
        for (Cell cell : value.rawCells()) {
           byte[] family = CellUtil.cloneFamily(cell);
           byte[] name = CellUtil.cloneQualifier(cell);
           byte[] val = CellUtil.cloneValue(cell);
            put.addColumn(family, name , val);

        }
        context.write(key, put);
    }
}
