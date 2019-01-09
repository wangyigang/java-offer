package month01.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;

public class HbaseApi {
    public static void main(String[] args) throws IOException {
        //1.导入依赖jar包
        //2.获取依赖对象
        Configuration conf = HBaseConfiguration.create();
        //添加配置项目--增加zookeeper相关配置
        conf.set("hbase.zookeeper.quorum", "hadoop102");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        //建立和hbase数据库的连接
        //降配置信息
        Connection connection = ConnectionFactory.createConnection(conf);
        System.out.println(connection);

        //操作hbase数据库
        //获取admin管理对象--类似于master
        Admin admin = connection.getAdmin();
        System.out.println(admin);
        TableName tn = TableName.valueOf("student");

        //判断表是否存在
        if (admin.tableExists(tn)) { //存在将原表删除
            //需要将表进行禁用
            admin.disableTable(tn);
            //将表进行删除
            admin.deleteTable(tn);
            System.out.println("删除完成..");
        }

        //可以进行创建表等操作
        //不能传入string类型，因为参数为string的是一个private对象
        HTableDescriptor desc = new HTableDescriptor(tn);
        //添加列族
        HColumnDescriptor columnDescriptor = new HColumnDescriptor("info");
        desc.addFamily(columnDescriptor);
        admin.createTable(desc);


        //向表中添加数据
        //shell: put 'student','1001','info:name','xxx'
        //增加数据之前获取表格对象
        Table table = connection.getTable(tn);
        //put操作
        String rowkey = "1001";
        byte[] byteRowkey = Bytes.toBytes(rowkey);
        Put put = new Put(byteRowkey);

        byte[] family = Bytes.toBytes("info");
        byte[] name = Bytes.toBytes("name");
        byte[] value = Bytes.toBytes("dilireba");
        put.addColumn(family, name, value);
        table.put(put);
        System.out.println("数据保存完成..");


        //查询数据
        Get get = new Get(byteRowkey);

        Result result = table.get(get);
        //result进行遍历
        for (Cell cell : result.rawCells()) {
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        }

        //删除数据
        Delete delete = new Delete(byteRowkey);
        table.delete(delete);
        System.out.println("删除完成...");
    }

}
