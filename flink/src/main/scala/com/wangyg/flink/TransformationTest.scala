package com.wangyg.flink


import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.scala._

/*
  Flink聚合需要引入窗口的概念，没有窗口，无法做到聚合
*/
object TransformationTest {
  val env = StreamExecutionEnvironment.getExecutionEnvironment

  def main(args: Array[String]): Unit = {
    //test1()
    //test2()
    //test3()
    //test4()
    //test5()
    //test6()
    //test7()
    //test8()
    //test9()
    test10()
    env.execute("TransformationTest")
  }

  //
  def test10(): Unit = {

  }

  //fold操作
  def test9(): Unit = {
    val stream = env.readTextFile("test01.txt")
    val streamFlatMap = stream.flatMap(x => x.split(" "))
    val streamMap = streamFlatMap.map(x => (x, 1))
    val streamKeyby: KeyedStream[(String, Int), Tuple] = streamMap.keyBy(0)
    //初始值100，然后每次初始值和其他值进行累加
    val streamFold: DataStream[Int] = streamKeyby.fold(100)((begin, item) => (begin + item._2))
    streamFold.print()
  }

  //reduce
  def test8(): Unit = {
    val stream = env.readTextFile("test01.txt").flatMap(item => item.split(" ")).map(item => (item, 1)).keyBy(0)

    val streamReduce = stream.reduce {
      (item1, item2) =>
        (item1._1, item2._2 + item1._2)
    }
    //进行打印
    streamReduce.print()
  }

  //keyby
  def test7(): Unit = {
    val stream = env.readTextFile("test01.txt")
    val streamFlatMap = stream.flatMap(
      x => x.split(" ")
    )
    //需要在进行以下转化操作
    val streaMap = streamFlatMap.map(x => (x, 1)) //keyby需要的是key-value类型的元素，所以进行一次map操作
    val streamKeyBy = streaMap.keyBy(0)
    streamKeyBy.print()

  }

  //union
  def test6(): Unit = {
    val stream = env.readTextFile("test00.txt")
    val streamFlatMap1 = stream.flatMap(x => x.split(" "))
    val stream2 = env.readTextFile("test01.txt")
    val streamFlatMap2 = stream2.flatMap(x => x.split(" "))
    val streamConnect: DataStream[String] = streamFlatMap1.union(streamFlatMap2)
    streamConnect.print()
  }

  //split分隔+ select
  def test5(): Unit = {
    val stream = env.readTextFile("test00.txt").flatMap(item => item.split(" "))

    val streamSplit = stream.split(
      num => //执行split后，进行拆分后，然后进行匹配，等于hadoop的放在一个List中
        // 字符串内容为hadoop的组成一个DataStream，其余的组成一个DataStream
        (num.equals("hadoop")) match {
          case true => List("hadoop")
          case false => List("other")
        })
    //使用select取出想要的一个--比filter更强大
    val hadoop = streamSplit.select("hadoop")
    val other = streamSplit.select("other")
    //    hadoop.print()


    print("---") //多线程环境下运行
    other.print()

  }

  //connect +comap
  def test4(): Unit = {
    val strem = env.generateSequence(1, 10)
    val stream2 = env.readTextFile("test00.txt")
      .flatMap(item => item.split(" "))
    val streamConnect: ConnectedStreams[Long, String] = strem.connect(stream2)
    val streamComap: DataStream[Any] = streamConnect.map(item => item * 2, item => (item, 1L))
    streamComap.print()
  }

  def test3(): Unit = {
    val stream = env.generateSequence(1, 10)
    val streamFilter = stream.filter(item => item != 1) //将1过滤掉
    streamFilter.print()
  }

  def test2(): Unit = {
    //flatmap
    val stream = env.readTextFile("test00.txt")
    val streamFlat = stream.flatMap(item => item.split(" "))
    streamFlat.print()
  }

  def test1(): Unit = {
    val stream: DataStream[Long] = env.generateSequence(1, 10)
    val streamMap: DataStream[Long] = stream.map(item => item * 2)
    streamMap.print() //map映射操作，每个进行*2
  }
}
