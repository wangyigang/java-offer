package day1211.ReduceJoin;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/*
 通过将关联条件作为Map输出的key，将两表满足Join条件的数据并携带数据所来源的文件信息，
发往同一个ReduceTask，在Reduce中进行数据的串联
 */
public class ReduceJoinBean implements WritableComparable<ReduceJoinBean> {
    private String id;
    private String pid;
    private int amount;
    private String pname;
    //bean中除了基本信息,还有判断文件名称信息
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

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

    @Override
    public String toString() {
        return  id + "\t" + pid +"\t"+ amount +
                "\t" + pname ;
    }

    /*
    排序：
    按照pid -》pname进行排序
     */
    @Override
    public int compareTo(ReduceJoinBean o) {
        int compare = this.pid.compareTo(o.getPid());
        if(compare == 0){
            return o.pname.compareTo(this.pname);
        }
        return compare;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeInt(amount);
        out.writeUTF(pname);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        id = in.readUTF();
        pid = in.readUTF();
        amount = in.readInt();
        pname = in.readUTF();
    }
}
