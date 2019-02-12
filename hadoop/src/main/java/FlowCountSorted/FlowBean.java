package FlowCountSorted;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements WritableComparable<FlowBean> {

    private long upFlow;
    private long downFLow;
    private long sumFlow;

    /**
     * 默认无参构造
     */
    public FlowBean() {
    }

    /**
     * 有参构造
     *
     * @param upFlow
     * @param downFLow
     */
    public FlowBean(long upFlow, long downFLow) {
        set(upFlow, downFLow);
    }

    public void set(long upFlow, long downFLow) {
        this.upFlow = upFlow;
        this.downFLow = downFLow;
        this.sumFlow = this.upFlow + this.downFLow;
    }

    /**
     * compareTo方法
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(FlowBean o) {
        //按照总流量的大小进行排序
        if (this.sumFlow > o.sumFlow) {
            return -1;
        } else if (sumFlow < o.sumFlow) {
            return 1;
        } else {
            return 0;
        }
    }


    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFLow() {
        return downFLow;
    }

    public void setDownFLow(long downFLow) {
        this.downFLow = downFLow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    @Override
    public String toString() {
        return upFlow +
                "\t" + downFLow +
                "\t" + sumFlow;
    }

    /**
     * 序列化方法
     *
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFLow);
        out.writeLong(sumFlow);
    }

    /**
     * 反序列化方法
     *
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        //顺数保持一致即可
        this.upFlow = in.readLong();
        this.downFLow = in.readLong();
        this.sumFlow = in.readLong();
    }
}
