package HbaseApi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.junit.Test;

public class HbaseApiTest {
    public static Configuration conf;


    @Test
    public void test(){
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hadoop201");

    }
}
