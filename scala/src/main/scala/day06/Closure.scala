package day06

/*
闭包：闭包就是一个函数和其他相关的引用环境组合的一个整体

(y:Int)=>x-y
返回的是一个匿名函数，一位该函数被引用到



 */
object Closure {
  def main(args: Array[String]): Unit = {

    //f函数就是闭包.
    val f = minusxy(20)
    println("f(1)=" + f(1)) // 19
    println("f(2)=" + f(2)) // 18
  }
  //1.用等价理解方式改写 2.对象属性理解
  def minusxy(x: Int) = (y: Int) => x - y
}
