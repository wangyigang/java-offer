package day1211.ReduceJoin;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class ReduceJoinGroupingComparator extends WritableComparator {
    /*
    构造函数：
    调用父类构造，构造时，使用reduceJoinbean类型进行创建两个对象，然后进行比较
     */
    protected ReduceJoinGroupingComparator() {
        super(ReduceJoinBean.class, true);
    }

    /*
    分组规则：
    分组按照pid进行分组，相同Pid进入同一组
     */
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        ReduceJoinBean oa = (ReduceJoinBean) a;
        ReduceJoinBean ob = (ReduceJoinBean) b;
        return oa.getPid().compareTo(ob.getPid());
    }
}
