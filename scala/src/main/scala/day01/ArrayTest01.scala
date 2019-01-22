package day01

import scala.collection.mutable.ArrayBuffer

object ArrayTest01 {
  def main(args: Array[String]): Unit = {
//    test01()
//    test02()
//    test03()
//    test04()
//    test05()
//    test06()



  }
  //多维数组的定义和使用
  def test06(): Unit ={
    val array= Array.ofDim[Int](1,2)
    array(1)(1)=11
  }
  //定长与变长数组的转变
  def test05(): Unit ={
    var array2 = ArrayBuffer[Int]()
    val array = array2.toArray //toArray变为定长数组
    val buffer = array.toBuffer //toBuffer变为可变数组


  }
  def test04(): Unit ={
    val array = ArrayBuffer[Any](3,2,5)
    for (i<- array){
      println(i)
    }
    println("array的长度="+array.length)
  }
  //变长数组
  def test03(): Unit ={
    val arr2 = ArrayBuffer[Int]()
    //追加元素
    arr2.append(6)
    arr2(0)=7 //需要开辟空间
    println(arr2)
  }

  //使用
  def test02(): Unit ={
    println("=======第二种方式=====")
    val  arr2 = Array(1,2)
    for (i<- arr2){
      println(i)
    }
  }

  def test01(): Unit ={
    val arr1 = new Array[Int](5)
    //赋值，集合元素采用小括号访问
    arr1(1)=1
    arr1(2)=2
    //遍历输出
    for(i<- arr1){
      println(i)
    }
  }
}

