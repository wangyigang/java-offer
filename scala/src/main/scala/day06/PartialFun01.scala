package day06

object PartialFun01 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, "abc")

    //思路1,使用map+fliter的思路
    def f1(n: Any): Boolean = {
      n.isInstanceOf[Int]
    }

    def f2(n: Int): Int = {
      n + 1
    }

    def f3(n: Any): Int = {
      n.asInstanceOf[Int]
    }

    val list2 = list.filter(f1).map(f3).map(f2)
    println("list2=" + list2)
  }
}



