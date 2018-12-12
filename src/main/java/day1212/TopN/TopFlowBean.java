package day1212.TopN;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TopFlowBean implements WritableComparable<TopFlowBean> {
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    public void set(long upFlow, long downFlow){
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = this.upFlow + this.downFlow;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    @Override
    public String toString() {
        return  upFlow +
                "\t" + downFlow +
                "\t" + sumFlow;
    }

    /*
    使用总流量进行排序
    逆序进行排序：从达到小
     */
    @Override
    public int compareTo(TopFlowBean o) {
        return Long.compare(o.getSumFlow(), this.sumFlow);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }
}
