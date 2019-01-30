package day04

import scala.collection.mutable.ListBuffer

object TestListBuffer {
  def main(args: Array[String]): Unit = {
    val list0 = ListBuffer[Int](1, 2, 3)
    println("list="+list0)
    var list1 =new ListBuffer[Int]
    list1+=4
    list1.append(5)

    println("===========  ++= =============")
    list0++= list1 //++表示加入的是集合中的各个元素
    println(list0) //将list1中的每一个元素加入到list0中

  }
}
