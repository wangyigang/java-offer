package day06

/*
偏函数：复合某个条件，而不是所有情况逻辑操作时，使用偏函数是
一个不错的选择
将包在大括号内的一组case语句封装为函数，成为偏函数
 */
object PartialFun03 {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, "abc") //数据类型为any
    val list2 = list.map(addOne)//list2=List(2, 3, 4, 5, ())
    println("list2=" + list2) // 不好的是返回值（）
    //希望本身带有过滤+处理的机制==>偏函数

  }
  def addOne(i: Any): Any = {
    i match {
      case x: Int => x + 1
      case _ =>
    }
  }
}
