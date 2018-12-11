package day1211.groupingCompare;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;



/*
自定义分区规则：extends
 */
public class OrderGroupingCompare  extends WritableComparator {
    protected OrderGroupingCompare() {
        super(OrderBean.class, true);
    }

    //分区规则--按照orderID进行排序
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean oa = (OrderBean) a;
        OrderBean ob = (OrderBean) b;

        return oa.getOrderId().compareTo(ob.getOrderId());
    }
}
