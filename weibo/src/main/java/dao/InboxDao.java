package dao;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
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

    public void insertMessage(List<String> fanscode,String starcode, String rowkey) throws IOException {
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
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(starcode), Bytes.toBytes(rowkey));

            list.add(put);
        }
        table.put(list);

        //释放资源
        table.close();
        connection.close();

    }

    /**
     * 发送 多条信息
     * @param fanscode
     * @param starcode
     * @param list
     */
    public void sendMessages(String fanscode, String starcode, List<String> list) throws IOException {
        Connection connection = getConnection();

        //获取表数据
        TableName tn = TableName.valueOf("wangyg:inbox");
        Table table = connection.getTable(tn);
        //将结果放入到puts中，批量进行插入
        List<Put> puts = new ArrayList<>();
        int i=0;
        //将数据插入到inbox表中
        for (String weiborowkey : list) {
            Put put = new Put(Bytes.toBytes(fanscode));//当前表的rowkey是粉丝
            //添加数据
            put.addColumn(Bytes.toBytes("info"),
                    Bytes.toBytes(starcode),
                    System.currentTimeMillis()+(++i), //自己组装时间戳
                    Bytes.toBytes(weiborowkey));
            puts.add(put );
        }
        //批量提交puts，一次性提交，但是可能会有问题，如果插入速度太快，时间戳一致，会覆盖掉数据
        table.put(puts);

        //释放资源
        table.close();
        connection.close();

    }

    /**
     * 获取微博的rowkey主键，标识--inbox表中存储的就是当时rowkey，要通过rowkey找到相对应的内容，所以返回的rowkey
     * @param fanscode
     * @param starcode
     * @return
     */
    public List<String> getWeiboRowkeys(String fanscode, String starcode) throws IOException {
        Connection connection = getConnection();

        //获取表数据
        TableName tn = TableName.valueOf("wangyg:inbox");
        Table table = connection.getTable(tn);

        //通过get方法获取数据
        Get get = new Get(Bytes.toBytes(fanscode));
        get.addColumn(Bytes.toBytes("info"), Bytes.toBytes(starcode));
        get.setMaxVersions(5); //设置
        Result result = table.get(get);

        List<String> list = new ArrayList<>();
        for (Cell cell : result.rawCells()) {
            list.add(Bytes.toString(CellUtil.cloneValue(cell)));
        }

        //释放资源
        table.close();
        connection.close();

        return  list;
    }

    /**
     *  删除inbox表中关联数据
     * @param fanscode
     * @param starcode
     */
    public void deleteInbox(String fanscode, String starcode) throws IOException {
        //获取connection连接
        Connection connection = getConnection();

        //获取表数据
        TableName tn = TableName.valueOf("wangyg:inbox");
        Table table = connection.getTable(tn);

        // 通过delete

        //通过delete方法进行删除关注关系
        Delete delete = new Delete(Bytes.toBytes(fanscode));
        //删除所有的列
        delete.addColumns(Bytes.toBytes("info"), Bytes.toBytes(starcode));

        table.delete(delete);

        //释放资源
        table.close();
        connection.close();
    }
}
