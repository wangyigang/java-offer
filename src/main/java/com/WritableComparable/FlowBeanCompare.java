package com.WritableComparable;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/*
对输出结果进行 总流量进行排序。
 */
public class FlowBeanCompare implements WritableComparable<FlowBeanCompare> {
    private long upFlow;
    private long downFlkow;
    private long sumFlow;
    //无参构造
    public FlowBeanCompare() {
    }
    //set方法设置
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
        return upFlow+"\t"+downFlkow+"\t" +sumFlow;
    }

    /*
    按照总流量进行排序从大到小进行排序
     */
    @Override
    public int compareTo(FlowBeanCompare o) {
        return Long.compare(o.getSumFlow(), this.sumFlow);
    }
    /*
    序列化
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlkow);
        out.writeLong(sumFlow);
    }
    /*
    反序列化
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlkow = in.readLong();
        sumFlow = in.readLong();
    }
}
