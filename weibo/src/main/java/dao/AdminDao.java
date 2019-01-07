package dao;

import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.NamespaceNotFoundException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;

public class AdminDao extends  BaseDao{
    /**
     * 创建名称空间
     */
    public void createNameSpace() throws IOException {
        //获取连接
        Connection connection = getConnection();
        Admin admin = connection.getAdmin();//获取admin管理对象

        //创建名称空间--namespace只能是[a-zA-Z_0-9]: wangyg-b 爆出IOException: Illegal character
        String namespace = "wangyg";

        //先判断名称空间是否存在
        try {
            admin.getNamespaceDescriptor(namespace); //查看源码后，发现如果namespace不存在，会爆出NamespaceNotFoundException异常
        }catch (NamespaceNotFoundException e) { //如果抛出异常，则说明表不存在
            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespace).build();
            admin.createNamespace(namespaceDescriptor); //创建create
        }finally {
            //进行释放资源
            admin.close();
            connection.close();
        }

    }


}
