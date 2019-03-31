package day09

object AnonymousFunTest {
  def main(args: Array[String]): Unit = {
    //  test1()
    //    test2()
    //    test3()
    //    test4()
    test5()
  }

  //不需要写返回类型，使用类型推倒  => 不需要写def 函数名
  def test5(): Unit = {
    val triple = (x:Double)=>3*x
    println("triple="+triple(3))
  }

  //高阶函数还可以返回一个匿名函数
  def test4(): Unit = {
    def minusxy(x: Int) = {
      (y: Int) => x - y
    }

    val result = minusxy(3)
    val i: Int = result(5)
    println(result) //返回function1 --因为只有一个参数
    println(i)
  }

  def test3(): Unit = {
    def minusxy(x: Int) = {
      (y: Int) => x - y
    }

    val result = minusxy(3)
    val i: Int = result(5)
    println(result)
    println(i)
  }

  def test2(): Unit = {
    def test(f: Double => Double, f2: Double => Int, n1: Double) = {
      f(f2(n1))
    }

    def sum(d: Double): Double = {
      d + d
    }

    def mod(d: Double): Int = {
      d.toInt % 2
    }

    //调用过程: 将三个参数传递给test, 第一个和第二个为函数， 传递完成后
    // f(f2(n1)) 先执行里面的f2(n1) 结果再传递个f() 继续执行,最后结果为0
    var res = test(sum, mod, 6.0)
    println("res=" + res)
  }


  def test1(): Unit = {
    val f1 = (n1: Int, n2: Int) => {
      println("匿名函数被调用...")
      n1 + n2
    }
    println("f1 类型=" + f1)
    println(f1(10, 30))
  }
}
