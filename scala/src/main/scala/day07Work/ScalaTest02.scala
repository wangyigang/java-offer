package day07Work

/*
2、定义一个可变类Pair[T]，带一个swap方法，交换对偶中组件的位置。
 */
object ScalaTest02 {
  def main(args: Array[String]): Unit = {
    val p = new Pair2(97 -> 'a')
    println(p.swap)

//    p.p = 98 -> 'b'
//    println(p.swap)
  }
}
class Pair2[T](var p: (T, T)) {

  def swap = {
    (p._2, p._1)
  }

}