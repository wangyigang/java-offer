package datastructure.sort

object MergeSort {
  def main(args: Array[String]): Unit = {
//    var arr = Array(5, 7, 4)
    var arr = Array(1,3,5,7,2,4,6,8)
    mergeSort(arr,0, arr.length-1)
    println(arr.mkString(" "))
  }
  def mergeSort(array: Array[Int], left:Int, right:Int): Unit ={
    var tmp = new Array[Int](array.length)
    mergeSort(array, left, right, tmp)
  }

  def merge(array: Array[Int], left: Int, mid: Int, right: Int, tmp: Array[Int]): Unit = {
    //三个索引指针，第一个指向left 起始位置，第二个指向mid+1 的起始位置，第三个指向tmp的起始位置
    var l = left
    var m = mid+1
    var t = 0

     //先进行比较两个部分的较小值，然后放入到tmp中，使变得有序
    while(l<= mid && m <= right){
      if (array(l)< array(m)){ //如果l的数据小
        tmp(t)=array(l)
        t+=1 //将较小的数据放入后，t指针向后移动一个单位
        l+=1 //l向后移动一个单位
      }else {
        tmp(t) =array(m)
        m+=1 //m指针向后移动一个单位
        t+=1  //t指针向后移动一个单位
      }
    }
    //然后判断这两个部分数据，是否还有剩余--有剩余将之放入到tmp中
    while(l<=mid){
      tmp(t) = array(l)
      l+=1
      t+=1
    }
    while(m<= right){
      tmp(t) = array(m)
      m+=1
      t+=1
    }
    //最后将数据从tmp拷贝到array中
    t=0
    var tmpLeft = left
    while(tmpLeft<=right){ //拷贝数据
      array(tmpLeft) = tmp(t)
      tmpLeft+=1
      t+=1
    }
  }

  def mergeSort(array: Array[Int], left:Int, right:Int, tmp:Array[Int]): Unit ={
    var mid = (left+right)/2
    if (left<right) {
      mergeSort(array, left, mid, tmp)
      mergeSort(array, mid + 1, right, tmp)
      //merger合并操作
      merge(array, left, mid, right, tmp)
    }
  }
}
