package datastructure.sort

import java.text.SimpleDateFormat
import java.util.Date


object QuickSortTest {
  def main(args: Array[String]): Unit = {
    test1()
//    test2()
  }
  def test1(): Unit ={
    var arr = Array(1, 3, 2, 7, 9, 5,4)

    quickSort(arr, 0, arr.length - 1)
    println(arr.mkString(" "))
  }
  def test2(): Unit ={
    val random = new util.Random()
    val arr = new Array[Int](80000000)
    for (i <- 0 until 80000000) { //循环的生成随机数
      arr(i) = random.nextInt(8000000)
    }
    println("排序前")
    val now: Date = new Date()
    val dateFormat: SimpleDateFormat =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.format(now)
    println("排序前时间=" + date) //输出时间

    quickSort(arr, 0, arr.length -1) // 调用快排
    println("排序后")

    val now2: Date = new Date()
    val date2 = dateFormat.format(now2)
    println("排序后时间=" + date2) //输出时间
    /*
    排序前
    排序前时间=2019-02-14 20:58:15
    排序后
    排序后时间=2019-02-14 20:58:29
     */
  }

  def quickSort(array: Array[Int], left: Int, right: Int): Unit = {
    if (left >= right)
      return
    val key = SinglePartSort(array, left, right)
    quickSort(array, left, key - 1)
    quickSort(array, key + 1, right)

  }

  def SinglePartSort(a: Array[Int], l: Int, r: Int): Int = {
    var left = l
    var right = r //不能指向常量
    var key = a(right)

    while (left < right) {
      while (left < right && a(left) <= key) {
        left += 1
      }
      a(right) = a(left) //交换
      while (left < right && a(right) >= key) {
        right -= 1
      }
      a(left) = a(right)
    }
    a(left) = key //最后left一定等于right,所以理论上，这里写left还是right都是一样的
    return left
  }
}
