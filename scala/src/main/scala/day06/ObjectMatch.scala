package day06

/*
unapply方法（对象提取器）返回some ,匹配成功g
返回None集合表示匹配失败
apply：对象构建器
 */
object ObjectMatch {
  def main(args: Array[String]): Unit = {
    val number: Double = 36.0
    number match {
      case Square(n) => println(n)
      case _ => println("nothing matched")
    }
  }
}

//对象匹配
object Square {
  def unapply(z: Double): Option[Double] = Some(math.sqrt(z))

  def apply(z: Double): Double = z * z
}