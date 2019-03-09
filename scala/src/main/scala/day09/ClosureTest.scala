package day09

object ClosureTest {
  def main(args: Array[String]): Unit = {
//    //闭包
//    var f = makeSuffix(".jpg")
//    println(f("dog.jpg"))
//    println(f("cat"))

//    var f2 = minus(20)
////    println(f2(1))
//    f2 = minus(10)
//    println(f2(2))

//    abstractcontrolTest()

//    recursive()
//    recursive2()
//    recursive3()
    recursive4()
  }

  def factorial(num: Int): Int = {
    if (num==0) 1 else  num * factorial(num-1)
  }

  //递归求阶乘
  def recursive4(): Unit ={
    println(factorial(10))
  }




  //递归方式
//  def reverse(str: String): String = {
//    if(str.length==1) str else
//      reverse(str.tail) + str.head
//  }
  def reverse(str: String): String = {
   if(str.length==1) str else reverse(str.tail)+str.head
  }

  //反转字符串--递归方式
  def recursive3(): Unit ={
    var str = "abcdef"
    println(reverse(str))
  }



  def recursive2(): Unit ={
    println(mymax(List(1,-1,9)))
  }
  def mymax(xs: List[Int]):Int={
    if (xs.isEmpty)
      throw new NoSuchElementException
    if (xs.size ==1)
      xs.head
    else if (xs.head > mymax(xs.tail)) xs.head
    else mymax(xs.tail)

  }

  def mx(num: BigInt, sum: BigInt):BigInt = {
    if (num<=99999999L) return mx(num+1, sum+num)
    else return sum
  }

  //scala中不建议使用while 和do..while ， 而是使用递归方式处理
  def recursive(): Unit ={
    //计算1-n的和,使用常规while()完成
    var num=BigInt(1)
    var sum=BigInt(0)
    var res=mx(num,sum )
    println("res="+res)
  }



  //控制抽象实质上是参数是函数，  函数参数没有输入也没有返回值
  //再使用递归的特性，处理数据
  def util(condition: =>Boolean)(block: => Unit):Unit = {
    if (condition){
      block
      util(condition)(block)
    }
  }

  //函数作为scala一等公民的具体体现
  //实现类似while()循环函数
  def abstractcontrolTest(): Unit ={
    var x =10
    util(x>0){
      x-=1
      println("until x="+x)
    }
  }

  def minus( x: Int) = {
    (y: Int) => {
      x - y
    }
  }

  def makeSuffix(suffix: String) = {
    (filename: String) => {
      if (filename.endsWith(suffix)) {
        filename
      } else {
        filename + suffix
      }
    }
  }
}
