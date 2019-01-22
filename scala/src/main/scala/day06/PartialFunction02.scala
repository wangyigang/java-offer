
//偏函数简化
object PartialFunction02{
  def main(args: Array[String]): Unit = {
//    val list2 = List(1, 2, 3, 4, "ABC").collect(f2)
//    println(list2)
    val list3 = List(1, 2, 3, 4, "ABC").collect{case i:Int=>i+1}
    println(list3)


    //作为参数的函数--函数作为一个变量传入到另一个函数中，作为参数的函数的类型
    //是function1,即(参数类型)=>返回类型
    //说明
    def plus(x: Int) = 3 + x
    //说明
    val result1 = Array(1, 2, 3, 4).map(plus(_))
    println(result1.mkString(" ")) //以...为分隔

    //匿名函数--没有名字的函数就是匿名函数，可以通过函数表达式来设置匿名函数
    val triple=(x:Double)=>3*x
    println(triple(3))
    /*
    (x:Double)=>3*x 就是匿名函数
    (x:Double)是形参列表  =>是规定语法表示后面是函数体 3*x就是函数体
    =>后面是函数体
    triple是指向匿名函数的变量 triple指向匿名函数的变量
     */

    //匿名函数
    //请编写一个匿名函数，可以返回2个整数的和，并输出该匿名函数的类型
    val f1 = (n1: Int, n2: Int ) => {
      println("匿名函数被调用")
      n1 + n2
    }
    println("f1类型=" + f1)
    println(f1(10, 30))
  }
  //简化形式1：
  def f2: PartialFunction[Any, Int] = {
    case i: Int => i + 1 // case语句可以自动转换为偏函数
  }
}