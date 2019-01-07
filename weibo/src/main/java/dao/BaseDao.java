package dao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public abstract  class BaseDao {

    public Connection getConnection() throws IOException {
        //第一种方式：需要设置相关配置
        //第二种方式：直接将.xml文件放入到resources中--采用第二种
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        return connection;
    }

}
