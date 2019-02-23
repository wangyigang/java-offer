package sparksql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Encoder, Encoders, SparkSession}
import org.apache.spark.sql.expressions.Aggregator

/*
  注意点：错误一：忘记导入隐式转换，所以出现编译错误,由于插件的原因，在真正运行的时候才爆出来
          错误二：user中数据不能是int类型，只能是long类型，当前从文件获取，不能进行类型推断，所以会用bigint范围扩展，不能用int类型接收
 */
object UDAFClassTest {
  import org.apache.log4j.{Level, Logger}
  Logger.getLogger("org").setLevel(Level.ERROR)

  def main(args: Array[String]): Unit = {
    //强类型
    test1()
  }
  def test1(): Unit ={
    val conf = new SparkConf().setMaster("local[*]").setAppName("test")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    //需要导入隐式依赖，否则会报错
    import spark.implicits._

    //创建函数对象
    val avgClassUDAF = new AgeAvgClassUDAF
    //构建UDAF函数
    val column = avgClassUDAF.toColumn.name("avgName")

    //准备数据
    val dataFrame = spark.read.json("input/user.json")
    val userDs = dataFrame.as[User]
    userDs.select(column).show()

    //关闭资源
    spark.stop()
  }
}
case class User(name:String, age:Long)
case class AvgBuffer(var sum:Long, var count:Long)

/**
  * 强类型语言
  */
//泛型
class AgeAvgClassUDAF extends Aggregator[User, AvgBuffer, Double]{
  /**
    * 初始值
    * @return
    */
  override def zero: AvgBuffer = {
    AvgBuffer(0L, 0L)
  }

  /**
    * 同一个节点（分区）的数据合并
    * @param b
    * @param a
    * @return
    */
  override def reduce(b: AvgBuffer, a: User): AvgBuffer = {
    b.sum+= a.age
    b.count+=1
    b
  }

  override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
    b1.sum+=b2.sum
    b1.count+=b2.count
    b1
  }

  override def finish(reduction: AvgBuffer): Double = {
    reduction.sum.toDouble /reduction.count
  }

  override def bufferEncoder: Encoder[AvgBuffer] = {
    Encoders.product
  }

  override def outputEncoder: Encoder[Double] = {
    Encoders.scalaDouble
  }
}

