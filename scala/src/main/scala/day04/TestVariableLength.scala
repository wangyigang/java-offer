package day04

object TestVariableLength {
  def main(args: Array[String]): Unit = {
//    println(sum(1, 2, 3, 4, 5, 6, 7, 8, 9))
//    def f1 = "vensa"
//    println(f1)
    f1()
  }

  def sum(n1:Int, args:Int*) ={
    println("arg.slength:"+args.length)
    var sum=1
    for (elem <- args) {
      sum+= elem
    }
    sum
  }


  def f1(){
    println("hello")
  }
}

