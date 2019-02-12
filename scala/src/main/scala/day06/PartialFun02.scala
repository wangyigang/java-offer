package day06

/*
方式一：使用filter过滤+map映射的操作进行
 */
object PartialFun02 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, "abc")
    val ints = list.filter(f1).map(f2)
    println(ints)
  }
  //方法一：进行判断
  def f1(n:Any): Boolean ={
    n.isInstanceOf[Int]
  }
  //方法二：进行映射操作
  def f2(n:Any) :Int={
    n.asInstanceOf[Int] +1
  }
}



