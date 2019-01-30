package day03
/*
将  val names = List("pangdi", "wangyg", "Com") 集合中首字母为'C'的筛选到新的集合
 */
object FilterTest {
  def main(args: Array[String]): Unit = {
    val names = List("pangdi", "wangyg", "Com")
    val list = names.filter(startC)
    println("list="+list)
  }
  def startC(str:String) :Boolean={
    if(str.startsWith("C"))  true else false
  }
}

