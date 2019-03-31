package day09

object TestScala {
  def main(args: Array[String]): Unit = {
    //    test1()
    //    test2()
    //    test3()
    //    test4()
    //    test5()
    //    test6()
    //    test7()
    //    test8()
    //    test9()
    //    test10
    //    test11()
    //    test12()
    //    test13()
    test14()
  }
  //
  def test14(): Unit = {

  }

  //并行计算测试
  def test13(): Unit = {
    val result1 = (0 to 100).map { case _ => Thread.currentThread.getName }.distinct
    val result2 = (0 to 100).par.map { case _ => Thread.currentThread.getName }.distinct
    println(result1)
    println(result2)
  }

  /**
    * 并行计算
    */
  def test12(): Unit = {
    (1 to 5).foreach(println(_))
    println("---")
    //添加par方式，进行并行计算
    (1 to 5).par.foreach(println(_)) //顺序并不能保证--并行计算，充分体现并行计算
    val list = List(1, 2, 3, 4, 5)
    list.par.map(println(_))
  }

  //操作符重载
  def test11(): Unit = {
    val money = new Money;
    money + (10)
    money.+(10)
    println(money.money)

    println(money ++) //后置操作符重载

    println(!money)
  }

  class Money {
    var money: Int = 0

    //中置操作符重载
    def +(n: Int) = {
      this.money += n
    }

    //后置操作符重载
    def ++(): Int = {
      this.money += 1
      this.money
    }

    //前置操作符重载
    def unary_!() {
      this.money = -this.money
      this.money
    }

  }

  //操作符的扩展
  def test10(): Unit = {
    //基本介绍：如果想在变量名，类名德国定义中抵用语法关键字，可以配合反引号
    //中置操作符：
    val n1 = 1
    val n2 = 2
    var r1 = n1 + n2
    val r2 = n1.+(n2)
    println(r1)
    println(r2)
  }

  //对象匹配
  def test9(): Unit = {
    val number: Double = 36.0
    number match {
      case Square(n) => println(n) //进行模式匹配时，会调用unapply
      case _ => println("nothing matched..")
    }
  }

  object Square {
    //对象拆解方法--对象提取方法--对象提取器 范湖一个Option集合
    def unapply(z: Double): Option[Double] = Some(math.sqrt(z))

    //对象构造方法
    def apply(z: Double): Double = z * z
  }

  //对象提取器
  def test8(): Unit = {
    val nameString = "Alice,Bob,Thomas" //字符串
    nameString match {
      case Names(first, second, third) => {
        println(first + ":" + second + ":" + third)
      }
      case _ => println("nothing method")
    }
  }

  //for()
  def test7(): Unit = {
    val map = Map("a" -> 1, "b" -> 0, "c" -> 3)
    for ((k, v) <- map if v == 0) {
      println(k + "->" + v)
    }
  }

  //偏函数方式--
  def test6(): Unit = {
    val list = List(1, 2, 3, 4, "abc")
    var par = new PartialFunction[Any, Int] {
      //判断是否是int类型
      override def isDefinedAt(x: Any): Boolean = {
        x.isInstanceOf[Int]
      }

      override def apply(v1: Any): Int = {
        v1.asInstanceOf[Int] + 2
      }
    }
    val list2 = list.collect(par)
    println(list2)
  }

  //虽然使用模式匹配比较简洁，但是不够完美
  //使用模式匹配方式进行处理
  def test5(): Unit = {
    val list = List(1, 2, 3, 4, "abc")
    val list2 = list.map(addFilter)
    println(list2) //List(2, 3, 4, 5, ()) ：当类型不匹配时，会出现()unit,无法避免
  }

  def addFilter(i: Any): Any = {
    i match {
      case x: Int => x + 1
      case _ =>
    }
  }

  //方案特点：比较麻烦：需要进行不同类型之间的转换
  //偏函数第一个案例：
  def test4(): Unit = {
    val list = List(1, 2, 3, 4, "abc")
    //先进行filter:再进行map
    println(list.filter(f1).map(f3).map(f2)) //返回值类型是any类型，因为前面的输入类型是any类型
    //先将any类型转为int类型 --在进行数值操作
  }

  def f2(n: Int): Int = {
    n + 1
  }

  def f3(n: Any): Int = {
    n.asInstanceOf[Int]
  }


  def f1(n: Any): Boolean = {
    n.isInstanceOf[Int] //判断是否是int类型
  }

  //偏函数中的简化形式
  def test2(): Unit = {
    def f2: PartialFunction[Any, Int] = {
      case i: Int => i + 1
      case j: Double => (j * 2).toInt
    }

    val list = List(1, 2, 3.2, 4.5, "hello")
    val list2 = list.collect(f2)
    println("list2=" + list2)
  }

  //偏函数的简化形式2：
  def test3(): Unit = {
    val list = List(1, 2, 3, 4, 1.2, 2.5, "hello")
    val list2 = list.collect({
      case i: Int => i + 1
      case j: Double => (j * 2).toInt
      case k: String => k + ":haha"
    })
    println("list2=" + list2)
  }


  /**
    * 函数作为参数
    */
  def test1(): Unit = {
    val result = Array(1, 2, 3, 4).map(plus(_))
    println(result.mkString(",")) //map：每一个进行传入搭配plus函数中
  }

  def plus(x: Int) = 3 + x

  def testScalaExeeception(): Unit = {
    try {
      var r = 10 / 0
    } catch {
      case e: ArithmeticException => {
        println("捕获了除数为零的算术异常...")
      }
      case e: Exception => println("捕获了异常...")
    }
  }

}

object Names {
  def unapplySeq(str: String): Option[Seq[String]] = {
    if (str.contains(","))
      Some(str.split(","))
    else
      None
  }
}