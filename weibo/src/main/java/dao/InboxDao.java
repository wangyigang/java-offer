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
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class InboxDao extends   BaseDao {
    /**
     * 创建inboxDao :创建收件箱表--将受到关注的明星的信息
     */
    public void createTable() throws IOException {
        Connection connection = getConnection();
        Admin admin = connection.getAdmin();

        TableName tn = TableName.valueOf("wangyg:inbox");
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tn);
        //先进行判断，如果已存在，将原来表进行删除---测试性代码，删除无关紧要
        if(admin.tableExists(tn)){
            admin.disableTable(tn); //先将表进行禁用
            //然后删除掉
            admin.deleteTable(tn);
        }

        HColumnDescriptor family = new HColumnDescriptor(Bytes.toBytes("info")); //记得使用名称空间，否则默认存在default中
        hTableDescriptor.addFamily(family);
        family.setMaxVersions(5); //设置最大历史版本
        //设置最小历史版本5
        family.setMinVersions(5);
        //创建收件箱表时，需要制定version

        //删除后创建表
        admin.createTable(hTableDescriptor);

        //释放资源
        admin.close(); //关闭资源连接
        connection.close();// 关闭资源连接


    }

    public void insertMessage(List<String> fanscode, String rowkey) throws IOException {
        //获取连接connenection
        Connection connection = getConnection();
        TableName tableName = TableName.valueOf("wangyg:inbox"); //注意加名称空间
        //获取表对象
        Table table = connection.getTable(tableName);

        List<Put> list = new ArrayList<>();
        for (String s : fanscode) {
            //s是每一个fanscode， rowkey是每一个新微博的rowkey,行键，用于存储进去

            byte[] rowkeyByte = Bytes.toBytes(s);
            Put put = new Put(rowkeyByte);
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("content"), Bytes.toBytes(rowkey));

            list.add(put );
        }
        table.put(list);

        //释放资源
        table.close();
        connection.close();

    }
}
