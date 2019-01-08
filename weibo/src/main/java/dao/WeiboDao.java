package dao;


import javafx.scene.control.Tab;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        //对时间戳进行处理：使用一个小算法，将小 -->大   大 -->小

        //使用long.max_value－当前时间戳，即可反转时间
        String rowkeyString = starcode+"_"+ (Long.MAX_VALUE-System.currentTimeMillis());
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

    /**
     * 扫描微博，获取最新五条数据
     * @param starcode
     * @return
     */
    public List<String> scanWeibos(String starcode) throws IOException {
       //获取connection连接
        Connection connection = getConnection();
        //获取表
        TableName tn = TableName.valueOf("wangyg:weibo");
        Table table = connection.getTable(tn);

        //扫描数据
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(starcode));
        scan.setStopRow(Bytes.toBytes(starcode+"|"));

        ResultScanner scanner = table.getScanner(scan);//获取扫描对象
        //scanner对数据范围进行控制

        //创建返回结果容器
        List<String> rowkeys  = new ArrayList<>();

        //获取数据
        for (Result result : scanner) {
            byte[] row = result.getRow();// 获取rowkey
            rowkeys.add(Bytes.toString(row));
            if(rowkeys.size()>=5){
                break; //超过5条，直接结束添加
            }
        }

        //关闭资源
        table.close();
        connection.close();
        return rowkeys;
    }

    /**
     * 获取orwkey的微博--通过从inbox中获取的rowkey
     * @param rowkeys
     * @return
     */
    public List<String> getWeibos(List<String> rowkeys) throws IOException {
        List<String> content = new ArrayList<>();

        Connection connection = getConnection();
        TableName tn = TableName.valueOf("wangyg:weibo");
        Table table = connection.getTable(tn);


        for (String rowkey : rowkeys) {
            //通过rowkey查找微博的具体内容
            Get get = new Get(Bytes.toBytes(rowkey));
            Result result = table.get(get);
            for (Cell cell : result.rawCells()) {
                content.add(Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        return content;
    }
}
