package sparkcore.JDBCRDDTest

import java.sql.DriverManager

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

/*
有两点错误需要注意：一：url中协议格式： jdbc:mysql(子协议)://主机:端口号/数据库名
                 二：从结果集中获取数据时，一定要对应具体的类型,getString  getInt要和数据本身类型对应
 */
import org.apache.log4j.{Level, Logger}
object TestJDBCRDD {
  Logger.getLogger("org").setLevel(Level.ERROR)
  def main(args: Array[String]): Unit = {
    //jdbcRead()
    jdbcWrite()
  }
  //jdbc写操作
  def jdbcWrite(): Unit ={
    val conf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)
    //jdbc写操作
    //准备数据

    var rdd = sc.makeRDD(Array(("pangdi", "femail")))

    //以分区方式进行
    rdd.foreachPartition(t => {  //先分区

      //创建jdbc驱动
      Class.forName("com.mysql.jdbc.Driver")
      var url = "jdbc:mysql://hadoop102:3306/company"
      val connection = DriverManager.getConnection(url, "root","1")
      var sqlString = "insert into staff(name, sex) values(?,?)"
      val statement = connection.prepareStatement(sqlString) //预编译
      //设置相应蛇形
     t.foreach( data =>{ //再分区内数据
       //设置属性
        statement.setString(1,data._1)
        statement.setString(2,data._2)
       //设置完成后，进行执行
       statement.execute()
     })

    })


  }



  //读数据
  def jdbcRead(): Unit ={
    val conf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)
    //jdbc
    var url = "jdbc:mysql://hadoop102:3306/company"
    val driver = "com.mysql.jdbc.Driver"
    val username = "root"
    val password = "1"

    var sqlString = "select * from staff where id>= ? and id<= ?"

    val resultrdd = new JdbcRDD(sc,
      () => {
        Class.forName(driver) //driver写错了
        val connection = DriverManager.getConnection(url, username, password)
        connection
      },
      sqlString,
      1,
      10,
      1, //分区个数
      r => {
        //回调函数，返回的结果集
        (r.getInt(1), r.getString(2), r.getString(3))
      }
    )
    resultrdd.foreach(println)

    //关闭资源
    sc.stop()
  }






  /*
    注意点：insertData中的参数类型
            prepareStatement参数 预编译软件
   */
//  def jdbcWrite(): Unit ={
//    var conf = new SparkConf().setMaster("local[*]").setAppName("test")
//    var sc = new SparkContext(conf)
//
//    //写数据
//    //准备数据
//    val data = sc.parallelize(List(("wang","Female")))
//    data.foreachPartition(insertData)
//    //foreach 和foreachPartition
//
//    //事件的准备在driver中，事件的执行在Executor中
//  }
//  def insertData(iterator: Iterator[(String,String)]): Unit ={
//    Class.forName ("com.mysql.jdbc.Driver").newInstance()
//    val conn = java.sql.DriverManager.getConnection("jdbc:mysql://hadoop102:3306/company", "root", "1")
//    iterator.foreach(data => {
//      val ps = conn.prepareStatement("insert into staff(name,sex) values (?,?)")
//      ps.setString(1, data._1)
//      ps.setString(2, data._2)
//      ps.executeUpdate()
//    })
//  }

//  def jdbcRead(): Unit ={
//    //spark配置信息
//    val conf = new SparkConf().setMaster("local[*]").setAppName("test")
//    //创建spakrContext上下文信息
//    val sc = new SparkContext(conf)
//
//    var driver = "com.mysql.jdbc.Driver"
//    var url = "jdbc:mysql://hadoop102:3306/company"
//    var username = "root"
//    var password = "1"
//
//    var sqlString="select * from  staff where id>=? and id<= ?"
//    //创建JDBCrdd
//    val rdd = new JdbcRDD(
//      sc,
//      () => {
//        Class.forName(driver)
//        val connection = DriverManager.getConnection(url, username, password)
//        connection
//      },
//      sqlString,
//      1,
//      10,
//      1,
//      r => (r.getInt(1), r.getString(2), r.getString(3))
//    )
//    rdd.foreach(println)
//
//    //关闭资源
//    sc.stop()
//
//  }
}
