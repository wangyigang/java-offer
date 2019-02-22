package sparkcore.testaction

import org.apache.spark.{SparkConf, SparkContext}

object ActionTest {

  import org.apache.log4j.{Level, Logger}

  Logger.getLogger("org").setLevel(Level.ERROR)
  private val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
  private val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    //    test1()
    //    test2()
    //test3()
//    test4()
//    test5()
    test6()
    test7()
  }
  def test7(): Unit ={

  }


  //flatMap
  def test6(): Unit ={
    var rdd = sc.makeRDD(1 to 5)
    val resultRDD = rdd.flatMap(1 to _)
    resultRDD.glom().foreach(println)
  }

  //mappartitinsWithIndex
  def test5(): Unit ={
    val rdd = sc.makeRDD(1 to 10,3)
    val resultRDD = rdd.mapPartitionsWithIndex((index, items) => {
      items.map(x => {
        (index, x)
      })
    })
    resultRDD.foreach(println)
  }


  //mapPartitions--区别：以分区为单位，将数据进行处理
  def test4(): Unit ={
    var rdd = sc.makeRDD(1 to 4, 2);
    val resultRDD = rdd.mapPartitions(x => {
      x.map((_, 1))
    })
    resultRDD.foreach(println)

  }

  //map
  def test3(): Unit = {
    var rdd = sc.makeRDD(1 to 4)
    val redultRDD = rdd.map((_, 1))
    redultRDD.foreach(println)
  }

  //countBykey --行动算子
  def test2(): Unit = {
    var rdd = sc.makeRDD(List((1, 3), (1, 2), (1, 4), (2, 3), (3, 6), (3, 8)), 3)
    println(rdd.countByKey()) //按照key进行统计个数,返回List中1->count 每个key->count
  }

  //foreach
  def test1(): Unit = {
    val rdd = sc.makeRDD(1 to 5)
    rdd.foreach(println)
  }
}
