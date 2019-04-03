package day09

/**
  * list
  */
object TestList {
  def main(args: Array[String]): Unit = {
    //    test1()
    //test2()
    //test3()
    //test4()
    //test5()
//    test6()
    test7()
  }

  /**
    *
    */
  def test7(): Unit ={

  }

  //匹配优先级： 传入的优先级> 匹配的隐式参数优先级 > 默认值的优先级
  def test6(): Unit = {
    //同时有 传值> 隐式值> 默认值
    //隐式值传值时，不能有二义性, 如果三个都没有匹配，就会报错
  }

  //隐式值可以作用于多个函数形参,默认参数只能作用于一个函数之中
  def test5(): Unit = {
    //入门案例:
    implicit val str: String = "jack"

    def hello(implicit name: String): Unit = {
      println(name + ">hello")
    }
    //调用的时候，就会使用隐式参数，隐式参数可以作用于多个函数之中
    hello //使用隐式参数时 不要加()  //自动使用隐式参数--底层直接使用 hello(jack)
  }

  //测试+=
  def test4(): Unit = {
    val list4 = List(1, 2, 3)

  }

  //:::   会进行扁平化，然后放入到
  def test3(): Unit = {
    val list4 = List(1, 2, 3, "abc")
    val list5 = 5 :: 6 :: list4 ::: Nil
    println(list5) //:::会进行扁平化，然后将list中的每一个元素全部放入在新的容器中
  }

  //::  从右向左不断进行添加元素，
  def test2(): Unit = {
    val list4 = List(1, 2, 3, "abc")
    val list5 = 4 :: 5 :: 6 :: list4 :: Nil
    println(list5) //不会进行拆分，全部直接插入到list中 //从右向左进行添加
  }


  //方式一： :+ +:
  def test1(): Unit = {
    val list = List(1, 2, 3, "abc") //list本身灭有发生变化
    val list2 = list :+ 4 //:对应集合List 元素在后面表示放在后面
    println(list2) //将元素放在集合中最后
    val list3 = 4 +: list //元素放在前面表示添加在最前面
    println(list3) //将元素添加在最前面
  }
}
