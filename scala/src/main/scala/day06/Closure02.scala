package day06

/*
请编写一个程序，具体要求如下
编写一个函数 makeSuffix(suffix: String)  可以接收一个文件后缀名(比如.jpg)，并返回一个闭包
调用闭包，可以传入一个文件名，如果该文件名没有指定的后缀(比如.jpg) ,则返回 文件名.jpg , 如果已经有.jpg后缀，则返回原文件名。
要求使用闭包的方式完成
String.endsWith(xx)
闭包的好处:
  返回的匿名函数和makeSuffix的suffix变量组合成一个闭包，
  闭包的好处，传统方式每次都要传入多个值，闭包会保留上次使用的某个值，所以一次传入，就可反复使用

 */
object Closure02 {
  def main(args: Array[String]): Unit = {
    //使用闭包
    val f = myMakeSuffix(".jpg")
    println(f("小猫.jpg"))
    println(f("小狗"))
  }

  def myMakeSuffix(suffix:String)=
    (y:String)=>{
      if (y.endsWith(suffix)) y else y+suffix
    }

}
