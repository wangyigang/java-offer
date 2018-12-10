package com.GroupingComparator;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {
    //订单id  商品id	成交金额
    private String orderId;
    private String productId;
    private double price;

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
        return  orderId + "\t" +
                productId + "\t" +
                price ;
    }

    //排序方法
    @Override
    public int compareTo(OrderBean o) {

        int compare = orderId.compareTo(o.getOrderId());
        if(compare ==0){
            // 价格倒序排序
            return  price > o.getPrice() ? -1 : 1;
        }
        return compare;
    }

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
