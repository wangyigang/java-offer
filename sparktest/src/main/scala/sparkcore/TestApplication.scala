package sparkcore


import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object TestApplication {

  def main(args: Array[String]): Unit = {

    // 准备Spark配置信息
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test")

    // 创建Spark上下文对象
    val sc : SparkContext = new SparkContext(conf)

    //sc.makeRDD(1 to 3)

    // 统计出每一个省份广告被点击次数的TOP3
    // 读取广告点击数据文件
    // 此处的相对路径指向的是开发环境中项目（最顶层的项目）的根目录
    val linesRDD: RDD[String] = sc.textFile("D:\\java\\24_spark\\day03\\2.资料\\agent.log")

    // 将数据转换为特定的格式（(省份，广告)，数量）
    // flatMap算子是对一行数据进行扁平化操作，也就是一行数据不作为一个整体
    // map算子可以将一行数据当成一个整体
    val mapRDD: RDD[((String, String), Int)] = linesRDD.map(line => {
      val datas = line.split(" ")
      var province = datas(1)
      var advert = datas(4)
      ((province, advert), 1)
    })

    // 统计同一个省份中广告点击的数量（(省份，广告)，点击次数）
    val reduceRDD: RDD[((String, String), Int)] = mapRDD.reduceByKey(_+_)

    // 将数据结构进行转换方便后续操作（省份，(广告，点击次数）)
    //        val pToASRDD: RDD[(String, (String, Int))] = reduceRDD.map(t => {
    //            (t._1._1, (t._1._2, t._2))
    //        })

    val pToASRDD: RDD[(String, (String, Int))] =
      reduceRDD.map{
        case ((pv:String, ad:String), sum : Int) => (pv, (ad, sum))
      }

    // 同一个省份中，按照点击的次数对广告进行排序
    val groupRDD: RDD[(String, Iterable[(String, Int)])] = pToASRDD.groupByKey()
    // 取前三个
    val resultRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues(v => {
      v.toList.sortWith((t1, t2) => {
        t1._2 > t2._2
      }).take(3)
    })

    // 执行作业
//    resultRDD.foreach(t=>{
//      t._2.foreach(t1=>{
//        println(t._1 + "," + t1._1 + ", " + t1._2)
//      })
//    })
    reduceRDD.sortBy(x=> x._1._1)
    reduceRDD.saveAsTextFile("output")

    // 关闭资源
    sc.stop()
  }
}
