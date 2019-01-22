package day06

/*
基本介绍：
  能够接受函数作为参数的函数，叫做高阶函数
 高阶函数可以返回函数类型

 类型推断原则
 1.当类型是可以推断时，可以省略参数类型
 2.当传入的函数，只有单个参数时，可以省去括号
 3.变量只在=>右边出现一次，可以用_来代替

 */
object HigherFun01 {
  def main(args: Array[String]): Unit = {
//    val res = test(sum, 6.0)
//    println("res=" + res)
//
//    val result3 = minusxy(3)(5)
//    println(result3)
//    def minusxy(x: Int) = {
//      (y: Int) => x - y //匿名函数
//    }
    test01()
  }
  def test01(): Unit ={
    val list = List(1, 2, 3, 4)
    println(list.map((x:Int)=>x + 1)) //(2,3,4,5)
    println(list.map((x)=>x + 1))
    println(list.map(x=>x + 1))
    println(list.map(_ + 1))
    val res = list.reduce(_+_) //reduce函数
    println(res)
  }

  def test(f: Double => Double, n1: Double) = {
    f(n1)
  }

  //sum 是接收一个Double,返回一个Double
  def sum(d: Double): Double = {
    d + d
  }

}
