package day03

object ReduceLeftTest {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5)
    //方式一：
    // val res = list.reduceLeft(f1)
    //方式二：
    val res = list.reduceLeft(_ + _)
    println("res=" + res)
  }

  def f1(n1: Int, n2: Int): Int = {
    n1 + n2
  }
}

