package dao;


import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * 维护微博表信息
 * Rowkey           info
 * XXXX             XXXX
 */
public class WeiboDao extends  BaseDao {

    public void createTable() throws IOException {
        Connection connection = getConnection();
        Admin admin = connection.getAdmin();

        TableName tn = TableName.valueOf("wangyg:weibo");
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tn);
        //先进行判断，如果已存在，将原来表进行删除---测试性代码，删除无关紧要
        if(admin.tableExists(tn)){
            admin.disableTable(tn); //先将表进行禁用
            //然后删除掉
            admin.deleteTable(tn);
        }

        HColumnDescriptor family = new HColumnDescriptor(Bytes.toBytes("info")); //记得使用名称空间，否则默认存在default中
        hTableDescriptor.addFamily(family);


        //删除后创建表
        admin.createTable(hTableDescriptor);

        //释放资源

        admin.close(); //关闭资源连接
        connection.close();// 关闭资源连接


    }
    /*
    明星发布微博信息
     */
    public String publishWeibo(String starcode, String content) throws IOException {
        //获取连接对象
        Connection connection = getConnection();
        TableName tn = TableName.valueOf("wangyg:weibo");
        //获取表对象
        //获取表名
        Table table = connection.getTable(tn);
        //添加插入数据

        //进行rowkey处理startcode_时间戳
        String rowkeyString = starcode+"_"+System.currentTimeMillis();
        byte[] rowkey = Bytes.toBytes(rowkeyString);

        Put put = new Put(rowkey);//rowkey
        put.addColumn(Bytes.toBytes("info"),
                Bytes.toBytes("content"),
                Bytes.toBytes(content));

        table.put(put);

        //释放资源
        table.close();
        connection.close();
        return rowkeyString;
    }
}
