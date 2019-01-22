package day05Work

/*


9、给定整型列表lst，(lst :\ List[Int]())(_ :: _)得到什么?
(List[Int]() /: lst)(_ :+ _)又得到什么？如何修改他们中的一个，以对原列表进行反向排列?

 */
object TestScala07 {
  def main(args: Array[String]): Unit = {
    val lst = List(1,2,3,4,5)

    //fold函数将上一步返回的值作为函数的第一个参数继续传递参与运算，直到list中的所有元素被遍历。
    //折叠就是讲上一步返回的值作为函数的第一个参数继续参与运算
    println((lst :\ List[Int]())(_ :: _)) // :\以左边为基础，进行左折叠 :: 是追加的意思，所以从左到右一个一个的被放入

    println((List[Int]() /: lst)(_:+_)) //

    println((List[Int]() /: lst)((a,b) => b :: a))

  }
}
