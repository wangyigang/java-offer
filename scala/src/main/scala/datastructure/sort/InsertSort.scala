package datastructure.sort

object InsertSortTest {
  def main(args: Array[String]): Unit = {
    var array = Array(1,3,5,7,9,2,4,6,8)
    insertSort(array)

    println(array.mkString(" "))
  }

  /**
    * var insertIndex = i-1 //插入位置为前一个，从后向前查找插入  记得-1
    * @param array
    */
  //插入排序
  def insertSort(array: Array[Int]): Unit ={
    //从后向前
    for (i <- 1 until array.length){
      var insertIndex = i-1 //插入位置为前一个，从后向前查找插入
      var insertValue = array(i)
      while(insertIndex>=0  && insertValue < array(insertIndex)){
        array(insertIndex+1) = array(insertIndex) //后面的数据覆盖前面的数据
        insertIndex-=1 //向前移动
      }
      array(insertIndex+1) = insertValue
    }
  }

}
