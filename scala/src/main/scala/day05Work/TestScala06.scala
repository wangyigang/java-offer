package day05Work

import scala.collection.mutable

/*

8、实现一个函数，作用与mkStirng相同，提示：使用reduceLeft实现试试

 */
object TestScala06 {
  def main(args: Array[String]): Unit = {

  }
}

trait MyMkString{
  this:mutable.Iterable[String]=>
  def myMkString = if( this != Nil) this.reduceLeft(_ + _)
}

