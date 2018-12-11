package day1211.groupingCompare;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {
    private String orderId;
    private String productId;
    private double price;

    public OrderBean() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  orderId + "\t" + productId + "\t" + price ;
    }

    //先按照orderid 排序 然后按照price排序
    @Override
    public int compareTo(OrderBean o) {
        int compare = this.orderId.compareTo(o.getOrderId());
        if(compare ==0 ){
            return Double.compare(o.getPrice(), this.price);
        }
        return compare;
    }

    //序列化
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderId);
        out.writeUTF(productId);
        out.writeDouble(price);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        orderId = in.readUTF();
        productId = in.readUTF();
        price = in.readDouble();
    }
}
