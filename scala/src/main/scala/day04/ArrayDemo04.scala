package day04

object ArrayDemo04 {
  def main(args: Array[String]): Unit = {
    var arr = Array(10,21,32,4,15,46,17)
    println(arr.sum)  //求和
    println(arr.max)//求取最大值
    println(arr.min)//求取最小值

    println(arr.mkString) //tostring(),转为字符串类型
    println(arr.mkString(",")) //以,为分隔，调用toString()

    println("===========排序================")
    //排序
    val sorted = arr.sorted //调用arr.sorted方法
    println(sorted.mkString(","))

    println("=========降序排序==============")
    //降序
    val ints = arr.sortWith(_ > _) //使用arr.sortWich  >左边大于右边，降序
    println(ints.mkString(","))
  }




//  def main(args: Array[String]): Unit = {
//    var arr = Array(1,2,3,4,5,6).toBuffer
//    var arr2 = for (ele <- arr if(ele %2 == 0)) yield ele*ele
//    println(arr2)//ArrayBuffer(4, 16, 36)
//  }



//  def main(args: Array[String]): Unit = {
//    var arr = Array(1,2,3,4,5,6).toBuffer
//    //使用关键字yield产生一个新的数组缓冲--yield 将产生的数组累加到一个新的集合中
//    var arr2 = for(ele <- arr) yield ele* ele
//    println(arr2)
//  }
}
