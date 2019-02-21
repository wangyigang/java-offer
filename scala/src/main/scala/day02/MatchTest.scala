package day02

import scala.collection.mutable.ArrayBuffer

object MatchTest {
  def main(args: Array[String]): Unit = {
    //    test2()
    //    test3()
    //    test4()
    //    test5()
    test6()
  }

  //匹配元祖
  def test6(): Unit = {
    //请返回 (34, 89) => (89,34)
    for (pair <- Array((0, 1), (34, 89), (1, 0), (1, 1), (1, 0, 2))) {
      val result = pair match { //
        case (0, _) => "0 ..." // 表示匹配 0 开头的二元组
        case (y, 0) => y //表示匹配 0 结尾的二元组
        case (x, y) => (y, x)
        case _ => "other" //.默认
      }
      println(result)
    }
  }

  def test5(): Unit = {
    for (list <- Array(List(0), List(1, 0), List(88), List(0, 0, 0), List(1, 0, 0))) {
      val result = list match {
        case 0 :: Nil => "0" // 匹配的 List(0)
        case x :: y :: Nil => x + " " + y // 匹配的是有两个元素的List(x,y)
        case 0 :: tail => "0 ..." // 匹配 以0 开头的后面有任意元素的List
        case x :: Nil => List(x)
        case _ => "something else"

      }
      //      0
      //      1 0
      //      List(88)
      //      0 ...
      //      something else
      println(result)
    }
  }

  //匹配数组
  def test4(): Unit = {
    for (arr <- Array(Array(0), Array(1, 0), Array(0, 1, 0),
      Array(1, 1, 0), Array(1, 1, 0, 1))) {
      val result = arr match {
        case Array(0) => "0" //匹配只有0的数组
        case Array(x, y) => ArrayBuffer(y, x) //x + "=" + y //匹配有两个元素的数组
        case Array(0, _*) => "以0开头和数组"
        case _ => "什么集合都不是"
      }
      println("result = " + result)
    }
  }

  //类型匹配
  def test3(): Unit = {
    val a = 5
    val obj = if (a == 1) 1
    else if (a == 2) "2"
    else if (a == 3) BigInt(3)
    else if (a == 4) Map("aa" -> 1)
    else if (a == 5) Map(1 -> "aa")
    else if (a == 6) Array(1, 2, 3)
    else if (a == 7) Array("aa", 1) //Array[Any]
    else if (a == 8) Array("aa")

    //说明
    //1. 下面的方式是进行类型匹配
    //2. 这种按照类型匹配的应用场景，通常在网络并发的使用.
    //3. 执行流程  case a : Int => a
    //3.1 a = result
    //3.2 判断 a 的类型是不是 Int, 如果是就匹配成功，否则匹配失败.

    val result = obj match {
      case _: BigInt => Int.MaxValue //这里表示忽略匹配的变量值
      case a: Int => a
      case b: Map[String, Int] => "对象是一个字符串-数字的Map集合"
      //case c: Map[Int, String] => "对象是一个数字-字符串的Map集合"
      case d: Array[String] => {
        println(d.mkString(" "))
        "对象是一个字符串数组"
      }
      case e: Array[Int] => "对象是一个数字数组"
      case f: BigInt => Int.MaxValue
      case _ => "啥也不是"
    }
    println(result)
  }

  //模式中的变量
  def test2(): Unit = {
    val ch = 'V'
    val res = ch match {
      case '+' => println("ok~")
      // 1. mychar = ch  2.  然后再去匹配  3. 这样的语法，我们称为模式中的变量
      case mychar => println("ok~" + mychar)
      case _ => println("ok~~")
    }

  }

  //模式匹配
  def test1(): Unit = {
    for (ch <- "+-3!") {
      var sign = 0
      var digit = 0
      ch match {
        case '+' => sign = 1
        case '-' => sign = -1
        case _ if ch.toString.equals("3") => digit = 3
        case _ => sign = 2
      }
      println(ch + " " + sign + " " + digit)
    }
  }
}

