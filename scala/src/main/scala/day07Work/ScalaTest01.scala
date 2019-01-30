package day07Work

import java.util

/*
类型参数
1、定义一个不可变类Pair[T,S], 带一个swap方法，返回组件交换过位置的新对偶





4、给定可变类Pair[S,T]，使用类型约束定义一个swap方法，当类型参数相同时可以被调用。

数据结构
说明：将前一天布置的10个数据结构题，做一遍，要求至少能够看懂代码实现。

 */
object ScalaTest01 {
  def main(args: Array[String]): Unit = {
    //定义一个不可变类Pair[T,S], 带一个swap方法，返回组件交换过位置的新对偶
    val p = new Pair[Int,Char](97->'a')
    val unit = p.swap()
    println(unit)

  }

}

class Pair[T,S](val  p:(T,S)){
  def swap() ={
    (p._2,p._1)
  }
}
