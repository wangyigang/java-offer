package day09

object TestScala {
  def main(args: Array[String]): Unit = {

  }

  def testScalaExeeception(): Unit = {
    try {
      var r = 10 / 0
    } catch {
      case e: ArithmeticException => {
        println("捕获了除数为零的算术异常...")
      }
      case e: Exception => println("捕获了异常...")
    }
  }

}
