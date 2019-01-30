package day07Work

/*
3、给定类Pair[T, S] ，编写一个泛型方法swap，接受对偶作为参数并返回组件交换过位置的新对偶。
 */
object ScalaTest03 {
  def main(args: Array[String]): Unit = {
    val p = new Pair3

    println( p.swap( 97 -> 'a' ) )
  }

}
class Pair3[T, S] {
  def swap[T, S](p: (T, S)) = {
    (p._2, p._1)
  }

}

