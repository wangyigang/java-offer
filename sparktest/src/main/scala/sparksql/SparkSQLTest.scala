package sparksql

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, sql}

object SparkSQLTest {
  import org.apache.log4j.{Level, Logger}
  Logger.getLogger("org").setLevel(Level.ERROR)

  def main(args: Array[String]): Unit = {
    //创建sparkconf
    val conf = new SparkConf().setMaster("local[*]").setAppName("test")
    //创建sparksession
    val spark = SparkSession.builder().config(conf)
      .getOrCreate() //获取或者创建

    //增加隐式转换规则
    import  spark.implicits._


    val dataFrame = spark.read.json("input/user.json")
    val ds = dataFrame.as[Person]



//    dataFrame.show()
    ds.createOrReplaceTempView("person")

    spark.sql("select * from person ").show()

    //关闭资源
    spark.close()
  }
}

case class Person(var name:String, var age:Long)

