package day05Work


/*
6、编写一个函数，从一个整型链表中去除所有的零值。
 */
object TestScala04 {
  def main(args: Array[String]): Unit = {
    var ints = List(1,2,0,4,0,5)
    val list = wipeZero(ints)
    println(list)
  }

  def wipeZero(nums : List[Int]):List[Int]={
    //      nums.filterNot(_ == 0)
    nums.filter(_ !=0)
  }
}
