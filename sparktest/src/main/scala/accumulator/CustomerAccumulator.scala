package accumulator

import java.util

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.util.AccumulatorV2

object CustomerAccumulatorTest {
  def main(args: Array[String]): Unit = {
    // 准备Spark配置信息
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test")

    // 创建Spark上下文对象
    val sc : SparkContext = new SparkContext(conf)

    var rdd = sc.makeRDD(Array("hadoop","pangdi","dilireba","wangyg"))
    //声明累加器
    var acc = new CustomerAccumulator()
    //注册
    sc.register(acc)
    //rdd处理
    rdd.foreach(word =>{
      acc.add(word)
    })
    //获取
    println(acc.value)

    //关闭资源
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

  override def add(v: String): Unit = {
    if (v.contains("h")) {
      blackList.add(v)
    }
  }

  //合并
  override def merge(other: AccumulatorV2[String, util.ArrayList[String]]): Unit = {
    blackList.addAll(other.value)
  }

  //获取累加器的值
  override def value: util.ArrayList[String] = blackList
}