package HbaseAPITest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/*
    范围查询
 */
public class TestHbaseAPi_scan {
    public static void main(String[] args) throws IOException {
        //使用java程序访问hbase
        //获取configuration
        Configuration conf = HBaseConfiguration.create();

        //获取zookeeper的连接配置
        conf.set("hbase.zookeeper.quorum", "hadoop102");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        //建立和hbase数据库的连接
        Connection conn = ConnectionFactory.createConnection(conf);
        System.out.println(conn);
        //操作hbase数据库
        Admin admin = conn.getAdmin();

        //判断数据库中表是否存在
        TableName tn = TableName.valueOf("student1");
        boolean b = admin.tableExists(tn);

        //如果存在，就先删除掉表
        if (b) {
            //禁用表
            admin.disableTable(tn);
            //删除
            admin.deleteTable(tn);
        }
        //创建表
        HTableDescriptor tableDescriptor = new HTableDescriptor(tn);
        //添加列族--info
        HColumnDescriptor columnDescriptor = new HColumnDescriptor("info");
        tableDescriptor.addFamily(columnDescriptor);
        //添加列族--detail
        HColumnDescriptor detailColumnDES = new HColumnDescriptor("detail");
        tableDescriptor.addFamily(detailColumnDES);
        admin.createTable(tableDescriptor);

        //增加数据
        //获取table对象
        Table table = conn.getTable(tn);

        String rowkey = "1001";
        Put put = new Put(Bytes.toBytes(rowkey));

        //添加cf cn cv
        byte[] cf = Bytes.toBytes("info");
        byte[] cn = Bytes.toBytes("name");
        byte[] cv = Bytes.toBytes("zhangsan");
        //添加数据
        put.addColumn(cf, cn, cv);
        table.put(put);

        //添加第二个数据
        String rowkey1 = "1002"; // =>
        Put put1 = new Put(Bytes.toBytes(rowkey1));

        byte[] columnFamily1 = Bytes.toBytes("detail");
        byte[] column1 = Bytes.toBytes("name");
        byte[] value1 = Bytes.toBytes("atguigu-lisi");

        put1.addColumn(columnFamily1, column1, value1);

        table.put(put1);

        String rowkey2 = "1003"; // =>
        Put put2 = new Put(Bytes.toBytes(rowkey2));

        byte[] columnFamily2 = Bytes.toBytes("detail");
        byte[] column2 = Bytes.toBytes("name");
        byte[] value2 = Bytes.toBytes("atguigu-wangwu");

        byte[] columnFamily3 = Bytes.toBytes("info");
        byte[] column3 = Bytes.toBytes("name");
        byte[] value3 = Bytes.toBytes("atguigu-wangwu");

        put2.addColumn(columnFamily3, column3, value3);

        put2.addColumn(columnFamily2, column2, value2);

        table.put(put2);
        System.out.println("数据保存成功");
        //查询数据
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes("info"));

        ResultScanner resultScanner = table.getScanner(scan);

        for (Result result : resultScanner) {
            for (Cell cell : result.rawCells()) {
                System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }

    }
}


