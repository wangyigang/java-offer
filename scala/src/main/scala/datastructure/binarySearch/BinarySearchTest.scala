package datastructure.binarySearch

object BinarySearchTest {
  def main(args: Array[String]): Unit = {
    //二分查找数组前提，数组有序
    var arr = Array(1, 2,3,4,5,6,7,8,9)
    println(binarySearch(arr, 5, 0, arr.length - 1))
  }
  def binarySearch(array: Array[Int], find:Int, left:Int, right:Int):Int ={
    if (left>right){
      return -1
    }
    var mid = (left+right)/2
    //递归方式进行二分法查找
    if (array(mid) >find){ //中间值大于想要查找的值
      binarySearch(array,find, left,mid-1)
    }else if (array(mid) < find){
      binarySearch(array,find, mid+1,right)
    }else{
      println("找到...下标 "+mid +"值：" +array(mid))
      return mid
    }

  }
}


