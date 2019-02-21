package day02

object CurliTest {
  def main(args: Array[String]): Unit = {
//    println(mul(10)(2))

   val str1 = "hello"
    //隐式转换中的函数需要两个参数，第一个参数时string,第二个参数时一个函数,返回一个boolean
//    println(str1.checkEq("HELLO")(eqStr))
    println(str1.checkEq("HELLO")(_.equals(_))) //true
  }

  def mul(x:Int)(y:Int)=x*y

  //函数柯里化，一个参数起到一个作用
  //方式一
  def eqStr(str:String,str2:String): Boolean={
    str.equals(str2)
  }


  //隐式类--string->string，可以使用checkEq方法
  implicit class TestEq(s: String) {
    //这里体现
    def checkEq(ss: String)(f: (String, String) => Boolean): Boolean = {
      f(s.toLowerCase, ss.toLowerCase)
    }
  }
}

