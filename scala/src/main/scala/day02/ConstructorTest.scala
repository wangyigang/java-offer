package day02

object ConstructorTest {
  def main(args: Array[String]): Unit = {
    val person = new Person //如果没有通过主构造器的方式构造，就会调用恰当的辅助构造器
    person.show()
  }

}
class Person private (pName: String, pAge:Int){
  var name = pName
  var age= pAge

   def this(){
    this("pangdi",23)
  }

  def show(): Unit ={
    println("name:="+this.name + " age="+this.age)
  }
}
