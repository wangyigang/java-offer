package day06

/*
比较两个字符串在忽略大小写的情况下是否相等
 */
object Curry02 {
  def main(args: Array[String]): Unit = {
    val str1 = "HELLO"
    val str2 = "hello"

    println(str1.checkEq(str2)(_.equals(_)))

  }

  def eq(s1: String, s2: String): Boolean = {
    s1.equals(s2)
  }

  implicit class TestEq(s: String) {
    def checkEq(ss: String)(f: (String, String) => Boolean): Boolean = {
      f(s.toLowerCase, ss.toLowerCase)
    }
  }

  def eq2(s1: String)(s2: String): Boolean = {
    s1.toLowerCase == s2.toLowerCase
  }
}
