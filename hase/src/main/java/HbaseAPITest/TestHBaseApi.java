package HbaseAPITest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;

public class TestHBaseApi {
    @Test
    public void test() throws IOException {
        //1.导入依赖jar包，使用maven中导入依赖关系

        //获取configuration
        Configuration conf = HBaseConfiguration.create();

        //增加zookeeper的相关配置
        conf.set("hbase.zookeeper.quorum", "hadoop102");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        //建立和hbase数据库的连接
        Connection conn = ConnectionFactory.createConnection(conf);

        //打印连接
        System.out.println(conn);

        //操作Hbase数据库
        //获取管理对象
        Admin admin = conn.getAdmin();

        //判断表是否存在
        TableName tableName = TableName.valueOf("student1");
        boolean b = admin.tableExists(tableName);
        if(!b) { //表不存在，就创建
            //创建表--shell: create table tablename info
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            //添加列族
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("info");
            tableDescriptor.addFamily(hColumnDescriptor);
            admin.createTable(tableDescriptor);
        }else { //表已存在
            //删除原有的表
            //删除表前，先将表进行禁用
//            admin.disableTableAsync(tableName);
//            //禁用后在进行删除
//            admin.deleteTable(tableName);
            System.out.println("表已存在");
        }

        //增加数据--put 'student',1001 ,'info:name', 'value'
        //增加数据前获取表格对象
        Table table = conn.getTable(tableName);


        String rowkey = "1001";
        Put put = new Put(Bytes.toBytes(rowkey)); //Bytes工具默认使用utf-8进行编解码

        byte[] infos = Bytes.toBytes("info");
        byte[] names = Bytes.toBytes("name");
        byte[] value = Bytes.toBytes("zhangsan");
        put.addColumn(infos, names, value);
        table.put(put);// 可以进行单个的put 或者进行一次性Put多次
        System.out.println("数据保存成功...");

        //查询数据
        Get get = new Get(Bytes.toBytes(rowkey));
        Result result = table.get(get);
        //遍历result
        for (Cell cell : result.rawCells()) {
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        }

        //删除数据
        Delete delete = new Delete(Bytes.toBytes(rowkey));
        table.delete(delete);
        System.out.println("删除成功...");
    }

}
