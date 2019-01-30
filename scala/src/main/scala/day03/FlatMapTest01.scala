package day03

object FlatMapTest01 {
  def main(args: Array[String]): Unit = {
    val names = List("Alice","Bob","Nick")
    //作用于集合中每个元素的子元素--映射到新的集合中
    println(names.flatMap(upper))
  }
  def upper(s:String) ={
    s.toUpperCase
  }
}

