package day08


object GenericUst3 {
  def main(args: Array[String]): Unit = {
    val list = List("hello","world","scala")
    //获取list中间元素
    println(getEle[String](list)) //调用函数时，可以限定类型
  }
  //返回值类型自动类型推倒
  //T 使用泛型
  def getEle[T](l:List[T]) ={
    l(l.length/2) //返回list中间值
  }
}

