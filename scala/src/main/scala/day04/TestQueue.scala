package day04

import scala.collection.mutable

object TestQueue {
  def main(args: Array[String]): Unit = {
    //创建队列
    val q1 = new mutable.Queue[Int]
    println(q1)
    //追加元素
    q1+=1
    q1+=2
    //将List中的元素添加到队列中
    q1 ++= List(1,2,3)
    println(q1)

    //添加数组中的元素
    q1++= Array(5,6,7)
    println(q1)
    //使用方法进行添加元素
    q1.enqueue(100,200)// 参数时* ，可以添加无限个
    println("======= head ======")
    //返回队列头部元素
    println(q1.head) //返回头结点信息
    //删除队列头节点信息
    val head  = q1.dequeue()
    println(q1)

    println("======  tail =====")
    //返回队尾元素--除了队列头结点的所有元素组成的队列
    println("queue.tail = "+ q1.tail)
  }

}
