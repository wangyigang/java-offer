package month02.day03.FlowCount;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<Text,FlowBean, Text,FlowBean> {

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long sumUpFlow =0;
        long sumDownFlow=0;
        for (FlowBean value : values) {
            sumUpFlow+= value.getUpFlow();
            sumDownFlow+= value.getDownFlow();
        }
        //封装对象
        FlowBean flowBean = new FlowBean(sumUpFlow, sumDownFlow);
        context.write(key, flowBean);

    }
}
