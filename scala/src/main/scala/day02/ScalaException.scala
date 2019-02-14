package day02

object ScalaException {
  def main(args: Array[String]): Unit = {
    try{
      var res = 10/0
    }catch {
      case  e:ArithmeticException=> {
        println("算术异常="+e.getMessage)
      }
      case e:Exception => println("异常信息="+e.getMessage)
    }finally {
      println("finally...")
    }
  }
}

