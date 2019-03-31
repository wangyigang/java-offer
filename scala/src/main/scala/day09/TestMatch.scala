package day09

import scala.collection.mutable

object TestMatch {
  def main(args: Array[String]): Unit = {
    //    test1()
    //    test2()
    //    test3()
    //    test4()
    //    test5()
    //    test6()
    //    test7()
    //    test8()
    //    test9()
    test10()
  }

  def test10(): Unit = {

  }


  def test9(): Unit = {
    val set = Set(1, 2, 3) //不可变的set
    println(set)
    val set2 = mutable.Set(1, 2, 3)
    println(set2)
    //添加元素
    set2 += 6
    set2 += (5)
    set2.add(3)
    println(set2)

    //元素的删除
    set2 -= 2
    set2 -= (4)
    set2.remove(100)
    println(set2)

    //set的遍历
    for (elem <- set) {
      println(elem)
    }
  }


  /*
    map遍历：

   */
  def test8(): Unit = {
    val map4 = mutable.Map(("A", 1), ("B", 2), ("C", 3), ("D", 30));
    println("------第一种方式----")
    for ((k, v) <- map4) {
      println(k + " is mapped to " + v)
    }
    //第二种方式
    println("----第二种方式----")
    for (v <- map4.keys) {
      println(v)
    }
    println("----第三种方式----")
    for (v <- map4.values) println(v)
    println("-----第四种方式---")
    for (v <- map4) {
      println(v) //打印生成的是tuple
    }

  }

  /*

   */
  def test7(): Unit = {
    val map4 = mutable.Map(("A", 1), ("B", 2), ("C", 3), ("D", 30));
    map4("AA") = 20 //如果不存在就会添加进map中
    println(map4)
    //如果map中含所有key,就会进行修改操作
    map4("AA") = 40 //修改
    println(map4)
    //方式二：使用+=增加单个元素
    map4 += ("E" -> 20)
    println(map4)
    //删除元素--如果不存在也不会报错
    map4 -= ("A", "B", "C")
    println(map4)
  }

  /*
    方式1：使用map(key)
    方式2： 使用contains进行判断
    方式3： 使用map.get(key).get()  返回一个some
    方式4： 使用getOrElse("key",默认值)
   */
  def test6(): Unit = {
    val map4 = mutable.Map(("A", 1), ("B", 2), ("C", 3), ("D", 30));
    //方式一：使用map(key)
    //println(map4("A~")) //如果值不存在，就会抛出异常
    //方式二：使用contains方法检查是否存在key
    if (map4.contains("A")) {
      println("key存在= " + map4("A"))
    } else {
      println("key不存在:)")
    }
    //方式三：使用map.get(key).get 获取
    println(map4.get("A").get)
    //使用getOrelse方式获取--如果只是简单的希望得到一个值，就可以使用getOrElse
    println(map4.getOrElse("A~", "默认值"))
  }


  //构建不可变的map
  /*
    方式一： 创建不可变map
    方式二： 创建可变map (mutable.Map)
    方式三： 创建空的映射 : new scala.collection.mutalbe.HashMap[String, Int]
    方式四： 使用对偶元祖的方式进行创建Map
   */
  def test5(): Unit = {
    //方式一
    val map1 = Map("Alice" -> 10, "Bob" -> 20, "Tom" -> 30)
    println(map1) //有序的--不可变的map是有序的
    //方式二:
    var map2 = mutable.Map("Alice" -> 10, "Bob" -> 20, "Tom" -> 30)
    //创建空的映射
    val map3 = new scala.collection.mutable.HashMap[String, Int]
    println(map3)
    val map4 = mutable.Map(("A", 1), ("B", 2), ("C", 3), ("D", 30));
    println(map4)

  }

  //map
  /*
      map基本介绍：
      HashMap是一个散列表(数组+链表)

      scala中的map是不可变的Map是有序的， 可变的map是无序的
   */
  def test4(): Unit = {
    //key -value 类型支持任意类型，没有限制
    // 底层, 存储的是tuple(x,y)
    val map = mutable.Map("Alice" -> 10, "Bob" -> 20, "Tom" -> "北京")
    println(map) //因为是可变的，所以顺序无法保证
  }

  //返回队列中的元素
  /*
      返回队列中的第一个元素: q.head
      返回队列最后一个元素： a1.last
      返回队列的尾部： q1.tail  --尾部：除了第一个元素之外的其他元素，都是尾部元素
   */
  def test3(): Unit = {
    val q1 = new mutable.Queue[Int]()
    q1 += 9
    q1 ++= List(4, 5, 7) //9,4,5,7
    println(q1.head) //打印出队列的头部元素，不会改变队列中的元素
    println(q1.last) //打印出队列最后一个元素
    println(q1.tail) //返回队列的队尾元素
  }


  //测试队列
  /*
    总结： 入队列：enqueue
           出队列：dequeue
   */
  def test2(): Unit = {
    val q1 = new mutable.Queue[Int]()
    //    println(q1)  //空的队列
    //给队列增加元素
    q1 += 9
    //    println("q1="+q1)
    q1 ++= List(4, 5, 7)
    //    println("q1="+q1)

    //从队列中取出一个元素
    val queueElement: Int = q1.dequeue()
    //    println(queueElement)
    //    println(q1)
    //入队列
    q1.enqueue(100, 10, 100) //enqueue --入队列
    //输出打印
    println(q1)
  }

  //变量声明中的模式
  def test1(): Unit = {
    val (x, y) = (1, 2)
    //模式抽取--可以直接抽取到y的值
    println("y=" + y)
    //将bigint 除10给q  将bigint模除3的值给r --所以结果3 1
    var (q, r) = BigInt(10) /% 3

    println(q) //3
    println(r) //1

    val array = Array(1, 7, 2, 9)
    //总结：最后面是_*
    var Array(first, second, _*) = array
    println(first, second) //输出打印first second
  }
}
