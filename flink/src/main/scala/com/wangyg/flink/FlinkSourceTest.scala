package com.wangyg.flink

//一定要注意导入这个隐式转换的包
import org.apache.flink.streaming.api.scala._

/**
  * Flink学习测试
  */
object FlinkSourceTest {
  def main(args: Array[String]): Unit = {

    // 1. 创建环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // 2. 获取数据源（Source）
    //方式一：读文件方式
    //    val stream = env.readTextFile("test00.txt")
    //方式二：通过socket方式
    //val stream = env.socketTextStream("hadoop102", 11111)
    //方式三：通过集合方式
    val list = List(1, 2, 3, 4)
    //    val stream = env.fromCollection(list) //非有序
    val iter = Iterator(1, 2, 3, 4)
    //    val stream = env.fromCollection(iter)
    //方式四：通过generate自动生成
    val stream = env.generateSequence(1, 10)

    // 3. 打印数据（Sink）
    stream.print()

    // 4. 执行任务
    env.execute("FirstJob")
  }
}
