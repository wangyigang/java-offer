package HbaseMRtool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.util.ToolRunner;

public class TestHbaseMr {
    public static void main(String[] args) {
        Configuration conf = HBaseConfiguration.create();
        int status = 0;
        try {
            status = ToolRunner.run(new HbaseMRTools(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(status);
    }
}
