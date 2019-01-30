package com.atguigu.chapter17.contextbounds

object ContextBoundsDemo {

  //隐式参数
  implicit val personComparetor = new Ordering[Person] {
    override def compare(p1: Person, p2: Person): Int = {
      println("隐式值的 personComparetor 比较器被调用")
      p1.age - p2.age
    }
  }


  def main(args: Array[String]): Unit = {

    val p1 = new Person("mary", 30)
    val p2 = new Person("smith", 35)

    //完成p1 和 p2 的年龄的比较
    //上下文界定有三种写法

    val compareComm4 = new CompareComm4(p1, p2)
    println(compareComm4.greatter)

//    //方式2的使用方法
//    val compareComm5 = new CompareComm5(p1, p2)
//    println("compareComm5.greatter" + compareComm5.greatter)
//
//
//    //方式3的使用
//    val compareComm6 = new CompareComm6(p1,p2) //p1 =>Person
//    println("compareComm6.greatter=" + compareComm6.greatter)
  }
}

//一个普通的Person类
class Person(val name: String, val age: Int) {
  override def toString = this.name + "\t" + this.age
}

//方式1
//说明
//1. T: Ordering 表示 实现了 Ordering -> 实现 comparetor 接口
//2. implicit comparetor: Ordering[T] 隐式参数
class CompareComm4[T: Ordering](obj1: T, obj2: T)(implicit comparetor: Ordering[T]) {
  //编写了一个greatter 返回 较大的对象
  def greatter =
    if (comparetor.compare(obj1, obj2) > 0) obj1 else obj2
}

//方式2,将隐式参数放到方法内
class CompareComm5[T: Ordering](o1: T, o2: T) {
  def greatter = {
    //f1 是一个方法.
    def f1(implicit cmptor: Ordering[T]) = cmptor.compare(o1, o2)

    //使用f1
    if (f1 > 0) o1 else o2
  }
}


//方式3,使用implicitly语法糖，最简单(推荐使用)
class CompareComm6[T: Ordering](o1: T, o2: T) {
  def greatter = {

    //这句话就是会发生隐式转换，获取到隐式值 personComparetor
    //implicitly[Ordering[T]] 作用是进行上下文的匹配，如果有
    //Ordering[T] 类型 就会匹配到， 并赋给 comparetor
    val comparetor = implicitly[Ordering[T]] //Ordering[Person]

    if (comparetor.compare(o1, o2) > 0) o1 else o2
  }
}

