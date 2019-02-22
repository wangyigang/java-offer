package sparkcore.accumulator

import org.apache.spark.{SparkConf, SparkContext}

object TestAccumulator {
  import org.apache.log4j.{Level, Logger}
  Logger.getLogger("org").setLevel(Level.ERROR)
  def main(args: Array[String]): Unit = {
    // 准备Spark配置信息
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test")

    // 创建Spark上下文对象
    val sc : SparkContext = new SparkContext(conf)
    var rdd = sc.makeRDD(1 to 5)
//    println(rdd.reduce(_ + _))
    val sum = sc.longAccumulator("sum")
    rdd.foreach( x =>{
      sum.add(x) //加数据
    })
    println(sum.value)
  }
}
