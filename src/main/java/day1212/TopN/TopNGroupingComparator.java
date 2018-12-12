package day1212.TopN;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TopNGroupingComparator extends WritableComparator {

    protected TopNGroupingComparator() {
        super(TopFlowBean.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return 0;
    }
}
