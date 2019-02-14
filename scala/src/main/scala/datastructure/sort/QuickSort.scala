package datastructure.sort

object QuickSortTest {
  def main(args: Array[String]): Unit = {
    //    var arr = Array(1, 3, 5, 7, 9, 2, 4, 6, 8)
    var arr = new Array[Int](20)
    for (i <- 0 until arr.length) {
      arr(i) = 100 - i
    }
    quickSort(arr, 0, arr.length - 1)
    println(arr.mkString(" "))
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
    var key = a(left)
    while (left < right) {
      while (left < right && a(right) >= key) {
          right-=1
      }
      a(left) = a(right)
      while ( left < right && a(left) <= key) {
        left += 1
      }
      a(left) = a(right)
    }
    a(left) = key
    return left
  }
}
