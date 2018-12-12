package day1212.TopN;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class TopFlowReducer extends Reducer<TopFlowBean, Text, Text, TopFlowBean> {

    @Override
    protected void reduce(TopFlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iterator = values.iterator();
        for (int i = 0; i < 10 && iterator.hasNext(); i++) {
            Text next = iterator.next();
            context.write(next,key);
        }
    }
}
