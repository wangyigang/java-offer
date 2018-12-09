package com.hdfs.test;

import org.junit.Test;

public class TestMapReduce {
    /*
    mapreduce详细工作流程：
    1.对待处理文本
    2进行分片，以128M进行分割
    3.提交切片信息：job.split  jar  xml
    4.计算mapTask数量（由rm启动Mr appmaster ）
    5.每个maptask进行文本处理，默认使用textInputFormat
    6.
     */
    @Test
    public void  test(){

    }

}
