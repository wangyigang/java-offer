package day02


object Demo02 {
  def main(args: Array[String]): Unit = {

    println(sum(1,2,'-'))
  }

  //说明
  //1. 如果我们返回的类型是多种的，则推荐使用类型推导
  def sum(n1: Int, n2: Int, oper: Char)  ={

    //判断
    if (oper == '+') {
      n1 + n2
    } else if (oper == '-') {
      n1 - n2
    } else {
      null
    }
  }
}