package day05Work

/*
4、设置一个映射，其中包含你想要的一些装备，以及它们的价格。然后根据这个映射构建另一个新映射，采用同一组键，但是价格上打9折。
 */
object TestScala02 {
  def main(args: Array[String]): Unit = {
    // Map 键值对演示
    val price = Map("BMW" -> 100, "Alibaba" -> 90)

    val stringToDouble = price.map(f1)
    println(stringToDouble)

  }

  def f1(t: Tuple2[String, Int]): Tuple2[String, Double] = {
    val rst = t._2 * 0.9
    (t._1, rst)
  }

}
