package ReduceJoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/*
    通过反射的方式重新获取数据对象
 */
public class ReducejoinReducer extends Reducer<Text, ReduceJoinBean, NullWritable,ReduceJoinBean > {
    private String name ;
    @Override
    protected void reduce(Text key, Iterable<ReduceJoinBean> values, Context context) throws IOException, InterruptedException {

        List<ReduceJoinBean> list = new ArrayList<>();
        for (ReduceJoinBean value : values) {
            if(value.getFlag().startsWith("pid")){
                //获取数据
                name = value.getPname();
            }else {
                ReduceJoinBean bean = new ReduceJoinBean();
                //通过反射的方式获取数据
                try {
                    BeanUtils.copyProperties(bean,value );
                    list.add(bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        for (ReduceJoinBean bean : list) {
            bean.setPname(name);
            context.write(NullWritable.get(), bean);
        }
    }
}
