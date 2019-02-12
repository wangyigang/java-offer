package datastructure.stack

import scala.io.StdIn

object ArrayStackTest {
  def main(args: Array[String]): Unit = {
    test()
  }

  def test(): Unit ={

    //创建给栈
    val arrayStack = new ArrayStack(4)

    //测试栈的基本使用是否正确
    var key = ""
    while (true) {
      println("show: 表示显示栈")
      println("exit: 表示退出程序")
      println("push: 表示添加数据到栈")
      println("pop: 表示从栈取出数据")

      key = StdIn.readLine()
      key match {
        case "show" => arrayStack.list()
        case "push" => {
          println("请输入一个数")
          val value = StdIn.readInt()
          arrayStack.push(value)
        }
        case "pop" => {
          val res = arrayStack.pop()
          if (res.isInstanceOf[Exception]) {
            println(res.asInstanceOf[Exception].getMessage)
          }else {
            printf("取出的数为 %d\n", res)
          }
        }
        case "exit" => {
          System.exit(0)
        }
      }
    }

  }

}


class ArrayStack(size: Int) {
  val maxSize = size //栈的大小
  var stackArr = new Array[Int](maxSize)

  //栈顶，初始化为-1
  var top = -1

  //判断栈满
  def isFull(): Boolean = {
    top == maxSize - 1
  }

  //判断栈空
  def isEmpty(): Boolean = {
    top == -1
  }

  //入栈
  def push(num: Int): Unit = {
    if (isFull()) {
      //如果已经满了
      println("栈满...")
      return
    }
    top += 1 //初始时指向-1,所以先+=1
    stackArr(top) = num

  }

  //出栈
  def pop(): Any = {
    if (isEmpty()) {
      return new Exception("栈空")
    }
    val value = stackArr(top) //临时存放
    top -= 1 //然后将top--
    return value //最后返回value
  }

  //遍历栈
  def list(): Unit = {
    if (isEmpty()) {
      println("栈空,没有数据..")
      return
    }
    for (i <- 0 to top reverse) { //反转，从栈顶到栈底
      printf("stack[%d]=%d\n", i, stackArr(i))
    }
  }

}