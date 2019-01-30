package day03

/*
请将 val names = List("Alice", "Bob", "Nick") 中的所有单词，全部转成字母大写，返回到新的List集合中.
 */
object MapTest01 {
  def main(args: Array[String]): Unit = {
    val names=List("Alice","Bob","Nick")
    val names2 = names.map(upper)
    println("names2="+names2)
  }
  def upper(str:String):String={
    str.toUpperCase
  }
}
