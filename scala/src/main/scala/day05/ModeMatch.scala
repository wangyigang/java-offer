package day05


/*
模式匹配：
match:

 */
object ModeMatch {
  def main(args: Array[String]): Unit = {
    //    test01()
    //    test02()
    //    test03()
    //    test04()
    //    test05()
    //    test06()
    //    test07()//匹配数组
    //    test08() //匹配list列表
    test09() //匹配元组
  }

  def test09(): Unit = {
    for (pair <- Array((0, 1), (1, 0), (1, 1),(1,0,2))) {
      val result = pair match { //
        case (0, _) => "0 ..." // 表示匹配 0 开头的二元组
        case (y, 0) => y //表示匹配 0 结尾的二元组
        case _ => "other" //.默认
      }
      println(result)
    }
  }

  def test08(): Unit = {
    for (list <- Array(List(0), List(1, 0), List(0, 0, 0), List(1, 0, 0))) {
      val result = list match {
        case 0 :: Nil => "0"
        case x :: y :: nil => x + " " + y
        case 0 :: tail => "0..."
        case _ => "other..."
      }
      println(result)
    }
  }


  /*
  array(0)匹配只有一个元素且为0 的数组
  array(x,y)匹配数组有两元素，并且恋歌元素赋值为x,y
  array(0,_*)匹配数组以0开始
   */
  def test07(): Unit = {
    for (arr <- Array(Array(0), Array(1, 0), Array(0, 1, 0),
      Array(1, 1, 0), Array(1, 1, 0, 1))) {
      val result = arr match {
        case Array(0) => "0"
        case Array(x, y) => x + "=" + y
        case Array(0, _*) => "以0开头和数组"
        case _ => "什么集合都不是"
      }
      println("result = " + result)
    }
  }


  //类型匹配--可以匹配对象的任意类型，
  // 这样就避免了使用isInstaceOf 和asInstanceOf
  /*
  注意事项：
  Map[
  类型匹配中map 和list会发生类型擦除
  按照类型进行匹配: 首先
   */
  def test06(): Unit = {
    val a = 4
    val obj = if (a == 1) 1
    else if (a == 2) "2"
    else if (a == 3) BigInt(3)
    else if (a == 4) Map("aa" -> 1)
    else if (a == 5) Map(1 -> "aa")
    else if (a == 6) Array(1, 2, 3)
    else if (a == 7) Array("aa", 1)
    else if (a == 8) Array("aa")

    //首先进行赋值：将ojb的值赋值给case 后面的abcd..
    //然后判断类型是否匹配,否则，匹配失败
    //map和list两个都会进行类型擦除
    //编译器会预先检测是否有可能的匹配，如果没有则报错
    val result = obj match {
      case a: Int => a
      case b: Map[String, Int] => "对象是一个字符串-数字的Map集合"
      case c: Map[Int, String] => "对象是一个数字-字符串的Map集合"
      case d: Array[String] =>
        println()
        "对象是一个字符串数组"
      case e: Array[Int] => "对象是一个数字数组"
      case _: BigInt => Int.MaxValue //忽略匹配的变量值，
      case f: BigInt => Int.MaxValue
      case _ => "啥也不是"
    }
    println(result)


  }

  //模式中的变量--case关键字后跟着变量名，match表达式的值会赋值给变量

  def test05(): Unit = {
    val ch = 'V'
    ch match {
      case '+' => println("ok~")
      //
      case mychar => println("ok~" + mychar) //ch 会赋值给mychar
      case _ => println("ok~~")
    }

  }

  def test04(): Unit = {
    for (ch <- "+-3!") {
      var sign = 0
      var digit = 0
      ch match {
        //case _ 不能放在所有case的前面，一定要放在最后
        case _ => digit = 3 //如果case _放在第一个，就永远不会走其他流程

        case '+' => sign = 1
        case '-' => sign = -1
        // 说明..
      }
      println(ch + " " + sign + " " + digit)
    }

  }

  def test03(): Unit = {
    for (ch <- "+-3!") {
      var sign = 0
      var digit = 0
      ch match {
        case '+' => sign = 1
        case '-' => sign = -1
        // 说明..
        case _ => digit = 3
        case _ => sign = 2 //第一：编译没有报错，第二：这个case _ 永远不会走到
      }
      //
      println(ch + " " + sign + " " + digit)
    }

  }

  /*
    守卫:表达匹配某个范围的数据，就要在模式匹配中增加条件守卫
   */
  def test02(): Unit = {
    for (ch <- "+-3!") {
      var sign = 0
      var digit = 0

      ch match {
        //ch是每一个字符串
        case '+' => sign = 1
        case '-' => sign = -1
        case _ if ch.toString.equals("3") => digit = 3
        case _ => sign = 2

      }
      println(ch + " " + sign + " " + digit)
    }

  }

  //模式匹配类似于switch语法，但更加强大

  /*采用match关键字声明，每个分支采用case关键字进行声明，当需要匹配时，会
  从一一个case分支开始，如果匹配成功，就执行对应的逻辑代码，如果不成功，就
  继续下一个分支进行判断，如果所有case都不匹配，就会执行case_分支
 类似于default
 注意事项：如果所有case都不匹配，有没有写case _分支，就会抛出MatchError
  每个case中，不用break语句，自动中断case
  可以在match可以使用其他类型，而不仅仅是字符
  => 相当于:
  =>到下一个case，作为一个整体执行，{}可有可无,最好有
  */
  def test01(): Unit = {
    val oper = '#'
    val n1 = 20
    val n2 = 10
    var res = 0
    oper match {
      case '+' => res = n1 + n2
      case '-' => res = n1 - n2
      case '*' => res = n1 * n2
      case '/' => res = n1 / n2
      case _ => println("oper error") //case _:相当于default case _
    }
    println("res=" + res)
  }
}


