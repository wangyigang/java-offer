package day05Work

/*
5、编写一个函数 minmax(values:Array[Int]), 返回数组中最小值和最大值的对偶
 */
object TestScala03 {
  def main(args: Array[String]): Unit = {
    val array = Array[Int](1,2,3,4,5,6,7,8,9)
    val tuple = minmax(array)
    println(tuple)
  }
  def  minmax(values:Array[Int]): Tuple2[Int,Int] ={
    var min =Int.MaxValue
    var max =Int.MinValue
    for (i<- values){
      if (min> i) min =i
      if (max < i) max = i
    }
    (min,max)
  }
}
