package dao;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class RelationDao extends  BaseDao {

    /**
     * 关注明星
     * @param fanscode
     * @param starcode
     */
    public  void attendStar(String fanscode, String starcode) throws IOException {
        //获取连接
        Connection connection = getConnection();
        //获取表格对象
        TableName tn = TableName.valueOf("wangyg:relation");
        Table table = connection.getTable(tn);

        //向表格对象插入数据
        //粉丝增加明星关注
        String rowkey = fanscode;
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes("attend"),
                Bytes.toBytes(starcode),
                Bytes.toBytes(starcode)); //以动态列的形式，不明确指定列的名称
        table.put(put);

        //反之：明星增加粉丝的个数
        String rowkey2= starcode;

        Put put2 = new Put(Bytes.toBytes(rowkey2));
        put2.addColumn(Bytes.toBytes("fans"),
                Bytes.toBytes(fanscode),
                Bytes.toBytes(fanscode)); //以动态列的形式，不明确指定列的名称
        table.put(put2);

        //释放资源
        table.close();
        connection.close();
    }

    /**
     * 创建relation 关系表
     */
    public void createTable() throws IOException {
        Connection connection = getConnection();
        Admin admin = connection.getAdmin();

        TableName tn = TableName.valueOf("wangyg:relation");
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tn);
        //先进行判断，如果已存在，将原来表进行删除---测试性代码，删除无关紧要
        if(admin.tableExists(tn)){
            admin.disableTable(tn); //先将表进行禁用
            //然后删除掉
            admin.deleteTable(tn);
        }

        HColumnDescriptor attend = new HColumnDescriptor("attend"); //记得使用名称空间，否则默认存在default中
        hTableDescriptor.addFamily(attend);

        HColumnDescriptor fans = new HColumnDescriptor("fans"); //记得使用名称空间，否则默认存在default中
        hTableDescriptor.addFamily(fans);

        //删除后创建表
        admin.createTable(hTableDescriptor);

        //释放资源
        admin.close(); //关闭资源连接
        connection.close();// 关闭资源连接


    }

    public List<String> getAllfans(String starcode) throws IOException {
        //获取连接
        Connection connection = getConnection();

        //获取表
        TableName tn = TableName.valueOf("wangyg:relation");
        Table table = connection.getTable(tn);

        //查找数据
        String rowkey = starcode;
        Get get = new Get(Bytes.toBytes(rowkey));
        get.addFamily(Bytes.toBytes("fans"));
        Result result = table.get(get);

        List<String> list = new ArrayList<>();
        //返回fans的list
        for (Cell cell : result.rawCells()) {
            list.add(CellUtil.cloneValue(cell).toString());
        }
        return list;
    }
}
