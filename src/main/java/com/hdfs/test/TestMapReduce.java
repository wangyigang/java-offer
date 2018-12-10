package com.hdfs.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;


public class TestMapReduce {

    @Test
    public void  test(){
        //获取配置信息
        Configuration conf = new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(conf);
            fileSystem.mkdirs(new Path("/1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
