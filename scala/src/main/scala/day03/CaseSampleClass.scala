package day03

object CaseSampleClass {
  def main(args: Array[String]): Unit = {
//    test1()
    test2()
  }
  //copy方法类似于clone方法，生成一个与现有值相同的新对象
  def test2(): Unit ={
    var amt = new  Currency(3000.0, "RMB")
    val amt2 = amt.copy() //类似于clone，创建的对象和amt的属性一样
    println(amt2.toString)
    //还可以在copy的同时修改属性值
    val amt3 = amt.copy(value =8000.0)
    println(amt3)
  }


  def test1(): Unit ={
    for (amt <- Array(Dollar(1000.0), Currency(1000.0, "RMB"), NoAmount)) {
      val result = amt match {
        //说明
        case Dollar(v) => "$" + v //对象匹配 1000.0
        //说明
        case Currency(v, u) => v + " " + u // 1000.0 RMB
        case NoAmount => ""
      }
      println(amt + ": " + result)
    }
  }
}

//抽象类
abstract class Amount
//Dollar 样例类
case class Dollar(value: Double) extends Amount
//Currency 样例类
case class Currency(value: Double, unit: String) extends Amount
//NoAmount 样例类
case object NoAmount extends Amount
