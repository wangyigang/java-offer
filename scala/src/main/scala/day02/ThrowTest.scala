package day02

object ThrowTest {
  def main(args: Array[String]): Unit = {
//  test1()
    test2()
  }
  def test2(): Unit ={
    fun()
  }

  @throws(classOf[NumberFormatException])//等同于NumberFormatException.class
  def fun()  = {
    "abc".toInt
  }

  def test1(): Unit ={
    try {
      val res = test()
      println(res.toString+"1")
    }catch {
      case e:Exception=>{
        println(e.getMessage)
      }
    }
  }
  def test(): Nothing = {
    throw new Exception("不对")
  }

}
