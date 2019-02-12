package Partition;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements Writable {
    private long upFlow;
    private long downFlkow;
    private long sumFlow;

    //无参构造
    public FlowBean() {
    }
    //设置方法
    public void set(long upFlow, long downFlkow){
        this.upFlow = upFlow;
        this.downFlkow = downFlkow;
        this.sumFlow = this.upFlow+ this.downFlkow;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlkow() {
        return downFlkow;
    }

    public void setDownFlkow(long downFlkow) {
        this.downFlkow = downFlkow;
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
                " " + downFlkow +
                " " + sumFlow;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlkow);
        out.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlkow=in.readLong();
        sumFlow = in.readLong();
    }
}
