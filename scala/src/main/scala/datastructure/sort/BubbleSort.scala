package datastructure.sort

/**
  * 冒泡排序
  */
object BubbleSort {
  def main(args: Array[String]): Unit = {
    var arr = Array(1,3,5,7,9,2,4,6,8)
    bubbleSort(arr)
    println(arr.mkString(" "))
  }
  def bubbleSort(array: Array[Int]): Unit ={
    for (i<- 0 until array.length-1){
      for(j<- 0 until array.length-i-1){ //最大的已经排在最后面了，所以内层循环到arr.legth-i-1
        if (array(j) >array(j+1)){//交换
          val tmp = array(j)
          array(j) = array(j+1)
          array(j+1) = tmp
        }
      }
    }
  }
}
