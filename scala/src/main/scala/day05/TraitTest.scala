package day05

object TraitTest {
  def main(args: Array[String]): Unit = {
    val sheep = new Sheep
    sheep.sayhi()
    sheep.sayHello()
  }
}

trait SayHello{
  //抽象方法
  def sayhi()
  //具体方法
  def sayHello(): Unit ={
    println("hello...")
  }
}
class  Sheep extends  SayHello{
  override def sayhi(): Unit = {
    println("sheep say hi...")
  }
}