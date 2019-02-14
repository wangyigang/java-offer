package day02

object LazyDemo {
  def main(args: Array[String]): Unit = {
    lazy val res = sum(1, 2)
    println("--------------")
    println("res="+res) //在真正使用的时候才进行求res的值
  }

  def sum(n1: Int, n2: Int) = {
    println("sum 被调用")
    n1 + n2
  }
}


