package day1212.ReduceJoin;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/*
自定义分组
 */
public class ReducejoinGroupingComparator extends WritableComparator {
    protected ReducejoinGroupingComparator() {
        super(ReduceJoinBean.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        ReduceJoinBean oa = (ReduceJoinBean) a;
        ReduceJoinBean ob  = (ReduceJoinBean) b;
        return ob.getPid().compareTo(ob.getPid());
    }
}
