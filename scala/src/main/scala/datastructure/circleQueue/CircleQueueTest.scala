package datastructure.circleQueue

import scala.io.StdIn

object CircleQueueTest {
  def main(args: Array[String]): Unit = {
    val q = new CircleArrayQueue(4)

    //环形队列
    var key =""
    while(true){
      println("show: 显示队列")
      println("exit: 退出程序")
      println("add:  添加数据")
      println("get : 取出对首元素数据")
      println("head: 查看队列中所有元素")

      key = StdIn.readLine()
      key match { // 模式匹配
        case  "show" =>q.showQueue()
        case "exit"=> System.exit(0) //以正常模式退出
        case "add" => {
          println("请输入一个数字")
          val num = StdIn.readInt()
          q.addQueue(num)
        }
        case "get" => {
          val res = q.getQueue()
          if (res.isInstanceOf[Exception]) {
            println(res.asInstanceOf[Exception].getMessage)
          } else {
            println(s"取出数据是 $res")
          }
        }
        case "head" => {
          val res = q.headQueue()
          if(res.isInstanceOf[Exception]) {
            //显示错误信息
            println(res.asInstanceOf[Exception].getMessage)
          }else {
            println("队列头元素值为=" + res)
          }
        }

      }
    }
  }
}

class CircleArrayQueue(arrMaxSize: Int) {
  //定义数组，存放容量
  var maxSize = arrMaxSize
  val array = new Array[Int](maxSize) //以maxSize作为大小
  //两个变量--环形队列中两个指针front指针和rear指针，分别指向队列头元素和队列尾元素
  var front = 0
  var rear = 0

  //定义环形队列方法
  /**
    * 判断队列是否已满 --队列满的条件是一个公式: (rear+1)%maxsize == front
    * 因为是环形队列，所以使用模除的方法,并且将一个位空出，用作标记位
    */
  def isFull(): Boolean = {
    (rear + 1) % maxSize == front
  }

  /**
    * 判断是否为空--环形队列空的条件是两个指针指向同一个位置
    *
    * @return
    */
  def isEmpty(): Boolean = {
    rear == front
  }

  //添加数据到环形队列中
  /**
    * 添加到尾部
    * @param n
    */
  def addQueue(n: Int): Unit = {
    if (isFull()) {
      println("环形队列已满，无法添加...")
      return
    }
    //将数据添加进入
    array(rear) = n
    rear = (rear + 1) % maxSize //因为是环形队列方式，所以需要模除
  }

  def getQueue(): Any = {
    if (isEmpty()) {
      println("环形队列已空,没有数据")
      return new Exception("环形队列空...")
    }
    val tmp = array(front)
    front = (front + 1) % maxSize
    return tmp
  }



  /**
    * 显示队列中所有元素
    */
  def showQueue(): Unit ={
    //先考虑一般性的特殊情况
    if (isEmpty()){
      println("环形队列空，没有数据...")
      return
    }
    //从front开始，循环
    for (i <- front until  size()){
      printf("下标 %d 值%d \n" , i, array(i))
    }
  }
  def size(): Int = {
    (rear+maxSize -front)%maxSize
  }

  def headQueue(): Any ={
    if (isEmpty()){
      return new Exception("环形队列空...")
    }
    return array(front)
  }
}