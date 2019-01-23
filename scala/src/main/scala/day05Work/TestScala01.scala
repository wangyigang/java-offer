package day05Work

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/*

3. 给定一个整数数组，产出一个新的数组，包含原数组中的所有正值，以原有顺序排列，之后的元素是所有零或负值，以原有顺序排列。

 */
object TestScala01 {
  //3. 给定一个整数数组，产出一个新的数组，包含原数组中的所有正值，以原有顺序排列，之后的元素是所有零或负值，以原有顺序排列。
  def main(args: Array[String]): Unit = {
    var arr = Array(1,-2,3,-4,5,-6,7,-8)
    val unit = sortArray(arr)
    println(unit)
  }
  def sortArray(a:Array[Int]): ArrayBuffer[Int] ={
    val buffer = new ArrayBuffer[Int]();
    buffer ++= (for( i <- a if(i > 0)) yield i) //yield将元素存放入集合中
    buffer ++= (for(i<- a if (i<0)) yield  i)
    buffer
  }
}

