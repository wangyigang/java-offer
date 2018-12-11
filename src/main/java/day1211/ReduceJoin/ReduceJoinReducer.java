package day1211.ReduceJoin;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class ReduceJoinReducer  extends Reducer<ReduceJoinBean, NullWritable, ReduceJoinBean, NullWritable> {
    @Override
    protected void reduce(ReduceJoinBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        //当前文件经过shuflle之后排序，排序为pid ->pname
        Iterator<NullWritable> iterator = values.iterator();
        iterator.next();
        String pname = key.getPname();//获取第一行的pname  设置给其他列
        while (iterator.hasNext()){
            iterator.next();
            key.setPname(pname);
            context.write(key, NullWritable.get());
        }
    }
}
