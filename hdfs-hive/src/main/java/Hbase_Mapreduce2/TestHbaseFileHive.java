package Hbase_Mapreduce2;

import org.apache.hadoop.util.ToolRunner;

public class TestHbaseFileHive {
    public static void main(String[] args) throws Exception {
        //使用ToolRunner进行方法调用，调用MapReduce程序
        ToolRunner.run(new HbaseFileHiveMrTools(), args);
    }
}
