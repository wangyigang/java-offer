package day05

object TestScala {
  def main(args: Array[String]): Unit = {
    var list = List(1, 2, 3, 4)
    //println(list.map((x: Int) => x + 1))
    //1.类型可以推断：所以:int可以省去
    //2. 参数只有一个()可以省去
    //3.参数只在=>右边出现一次，可以使用_代替
    println(list.map(_ + 1))

    //对List求和
    /*
      反推：源代码
      参数只在右边出现一次，所以可以使用_代替
      类型可以被推断出，所以类型省去
     */
    //    println(list.reduce(_ + _))
    //    println(list.reduce((x,y)=> x + y))
//    println(list.reduce((x: Int, y: Int) => {
//      x + y
//    }
//    ))
    println(list.reduceLeft(_+_))

  }
}

