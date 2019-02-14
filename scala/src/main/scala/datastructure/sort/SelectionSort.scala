package datastructure.sort

object SelectionSort {
  def main(args: Array[String]): Unit = {
    testSelectSort()

  }
  def testSelectSort(): Unit ={
    var arr = Array(1,3,5,2,4)
    selectionSort(arr)

    println(arr.mkString(" "))
  }

  /**
    * 选择排序
    * @param array
    */
  def selectionSort(array: Array[Int]): Unit = {
    for (i <- 0 until array.length - 1) {
      var minValue = array(i)
      var minIndex = i
      //内层循环
      for (j <- (i + 1) until array.length) {
        if (minValue > array(j)) {
          minValue = array(j)
          minIndex = j
        }
      }
      //判断索引是否相同
      if (minIndex != i) {
        array(minIndex) = array(i)
        array(i) = minValue
      }
    }
  }
}
