package day08

object Convariant {
  def main(args: Array[String]): Unit = {
    //不变
    val t1: Temp3[Sub] = new Temp3[Sub]("hello")
    //    val t2:Temp3[Sub] = new Temp3[Super]("hello")
    //协变 super是sub的父类，Temp[super]是Temp[Sub]的父类
    val t4: Temp4[Super] = new Temp4[Sub]("hello")

    //逆变--super是sub的父类 temp[super]是super[sub]的子类
    val t5: Temp5[Sub] = new Temp5[Super]("hello")

  }
}

//逆变
class Temp5[-A](title: String) {
  override def toString: String = {
    title
  }
}

//协变
class Temp4[+A](title: String) {
  override def toString: String = {
    title
  }
}


//不变
class Temp3[A](title: String) {
  override def toString: String = {
    title
  }
}


class Super //父类

//sub是super的子类
class Sub extends Super