//package day08

//定义Person类，进行两个Person对象比较
class Person(val name: String, val age: Int) extends Ordered[Person] {
  //重写Compare方法
  override def compare(that: Person): Int = {
    //1 省略return
    println("compare方法被调用...")
    this.age - that.age
  }

  //重写tostring 方法
  override def toString: String = {
    this.name + "\t" + this.age
  }
}

//使用视图界定方式进行比较两个person类
class CompareComm2[T <% Ordered[T]](obj1: T, obj2: T) {
  def greater = if (obj1 > obj2) obj1 else obj2

  //使用compareTo方法
  def greater2 = if (obj1.compareTo(obj2) > 0) obj1 else obj2
}

object ViewBoundsTest02 {
  def main(args: Array[String]): Unit = {

    val jack = new Person("jack", 35)
    val tom = new Person("tom", 30)
    val compare = new CompareComm2(jack, tom)
    println(compare.greater)

  }
}
