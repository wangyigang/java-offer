package day08

object GenericUst {
  def main(args: Array[String]): Unit = {
    val mesg = new StrMessage[String]("10")
    println("mesg="+mesg.get)
    val mesg2 = new IntMessage[Int](20)
    println("mesg2="+mesg2.get)
  }
}
//定义抽象父类
abstract class Message[T](s:T){
  def get:T = s
}
//
class StrMessage[String](msg:String) extends Message(msg)

class IntMessage[Int](msg:Int) extends Message(msg)

