package sparkcore.broadcast

import java.util

import org.apache.spark.rdd.RDD
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

object BroadCastTest {

  import org.apache.log4j.{Level, Logger}

  Logger.getLogger("org").setLevel(Level.ERROR)

  def main(args: Array[String]): Unit = {
    //    test2()
    //    test1()
    //    test3()
    test4()
  }

  //没有执行action算子
  def test4(): Unit = {
    // 准备Spark配置信息
    val conf: SparkConf = new SparkConf().setMaster("local[1]").setAppName("Test")
    // 创建Spark上下文对象
    val sc: SparkContext = new SparkContext(conf)

    val rdd1: RDD[(String, Int)] = sc.makeRDD(Array(("a", 1), ("b", 1)))
    var arr = Array(("a", 3), ("b", 4))
    // var arrBroadcast = sc.broadcast(arr)

    //    //通过只写变量获取结果数据
    var acc = new CustomerAccumulator
    sc.register(acc)

    val resultRDD = rdd1.map(t => {
      val rddkey = t._1
      arr.foreach(t1 => {
        val key = t1._1
        if (rddkey.equals(key)) {
          var strResult = "(" + rddkey +","+ t._2 +","+ t1._2 + ")"
          acc.add(strResult)
        }
      })
    })
    //没有执行action算子
    resultRDD.collect()

    println(acc.value)

    //关闭
    sc.stop()
  }

  def test3(): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test")
    // 创建Spark上下文对象
    val sc: SparkContext = new SparkContext(conf)

    val rdd1: RDD[(String, Int)] = sc.makeRDD(Array(("a", 1), ("b", 1)))
    var arr = Array(("a", 3), ("b", 4))

    val resultRDD = rdd1.map(t => {
      val rddkey = t._1
      var tp: (String, (Int, Int)) = null
      arr.foreach(t1 => {
        val key = t1._1
        if (rddkey.equals(key)) {
          val tuple = (rddkey, (t._2, t1._2))
          tp = tuple
        }
      })
      tp
    })
    resultRDD.foreach(println)
    //关闭
    sc.stop()
  }

  def test2(): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("brocast")
    val sc = new SparkContext(conf)
    val list = List("hello hadoop")
    val broadCast = sc.broadcast(list)
    val lineRDD = sc.textFile("D:\\java\\24_spark\\spark资料\\wc.txt")
    lineRDD.filter { x => broadCast.value.contains(x) }.foreach {
      println
    }
    sc.stop()
  }

  def test1(): Unit = {
    // 准备Spark配置信息
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test")
    // 创建Spark上下文对象
    val sc: SparkContext = new SparkContext(conf)

    val rdd1: RDD[(String, Int)] = sc.makeRDD(Array(("a", 1), ("b", 1)))
    var arr = Array(("a", 3), ("b", 4))
    var arrBroadcast = sc.broadcast(arr)

    //    //通过只写变量获取结果数据
    var acc = new CustomerAccumulator()
    sc.register(acc)

    val resultRDD = rdd1.map(t => {
      val rddkey = t._1
      arrBroadcast.value.map(t1 => {
        val key = t1._1
        if (rddkey.equals(key)) {
          val strResult = "(" + rddkey + t._2 + t1._2 + ")"
          acc.add(strResult)
        }
      })
    })
    resultRDD.collect()

    println(acc.value)
    //关闭
    sc.stop()
  }
}


//泛型[in, out]
class CustomerAccumulator extends AccumulatorV2[String, util.ArrayList[String]] {
  var blackList = new util.ArrayList[String]()

  //是否是初始值
  override def isZero: Boolean = {
    blackList.isEmpty
  }

  //复制，拷贝
  override def copy(): AccumulatorV2[String, util.ArrayList[String]] = {
    var acc = new CustomerAccumulator()
    acc
  }

  //重置
  override def reset(): Unit = {
    blackList.clear()
  }

  //累加
  override def add(v: String): Unit = {
    blackList.add(v)
  }

  //合并
  override def merge(other: AccumulatorV2[String, util.ArrayList[String]]): Unit = {
    blackList.addAll(other.value)
  }

  //获取累加器的值
  override def value: util.ArrayList[String] = blackList
}