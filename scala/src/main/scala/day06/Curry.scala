package day06

/*
函数柯里化
基本介绍：
  函数接收多个参数的函数，都可以转化成接收单个参数的函数，这个过程叫做柯里化

 */
object Curry {
  def main(args: Array[String]): Unit = {
    //使用柯里化：编写一个函数，接收两个整数，可以返回两个数的乘积，要求:
    println(mulCurry2(10)(10))
  }
  //使用函数柯里化完成
  def mulCurry2(x:Int)(y:Int): Int ={
    x*y
  }
  //使用闭包的方式完成
  def mulCurry(x:Int)={
    (y:Int)=>{
      x*y //匿名函数
    }
  }
  //使用常规的方式完成
  def mul(x:Int,y:Int)={
    x*y
  }
//  def mul(x:Int,y:Int)=x*y
}
