package day1212.ReduceJoin;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ReduceJoinBean implements WritableComparable<ReduceJoinBean> {
    private String id;
    private String pid;
    private int amount;
    private String pname;
    //区分文件标志位
    private String flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return id +
                "\t" + pid +
                "\t" + amount +
                "\t" + pname +
                "\t" + flag ;
    }
    /*
    按照pid -》pname进行排序
     */
    @Override
    public int compareTo(ReduceJoinBean o) {
        int compare = this.getPid().compareTo(o.getPid());
        if(compare ==0){
            return o.getPname().compareTo(this.getPname());
        }else{
            return compare;
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeInt(amount);
        out.writeUTF(pname);
        out.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        id = in.readUTF();
        pid = in.readUTF();
        amount = in.readInt();
        pname = in.readUTF();
        flag = in.readUTF();
    }
}
