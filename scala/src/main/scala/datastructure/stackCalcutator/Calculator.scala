package datastructure.stackCalcutator

object Calculator {
  def main(args: Array[String]): Unit = {
    //val expression = "3101+4*(6-2)" => 开阔思路[编程]
    val expression = "7*2*2-5+1-5+3-4"

    val numStack = new ArrayStack(10)
    val operStack = new ArrayStack(10)
    //使用两个栈进行处理
  }
}



//栈，该栈已经测试过了
class ArrayStack(size: Int) {
  val maxSize = size // 栈的大小
  var stack = new Array[Int](maxSize)
  //栈顶, 初始化为-1
  var top = -1

  //栈满
  def isFull(): Boolean = {
    top == maxSize - 1
  }

  //栈空
  def isEmpty(): Boolean = {
    top == -1
  }

  //入栈, 放入数据
  def push(value: Int): Unit = {
    if (isFull()) {
      println("栈满")
      return
    }
    top += 1
    stack(top) = value
  }

  //出栈, 取出数据
  def pop(): Any = {
    if (isEmpty()) {
      return new Exception("栈空")
    }
    val value = stack(top)
    top -= 1
    return value
  }

  //遍历栈
  def list(): Unit = {
    if (isEmpty()) {
      println("栈空，没有数据")
      return
    }
    for (i <- 0 to top reverse) {
      printf("stack[%d]=%d\n", i, stack(i))
    }
  }

  //返回运算符的优先级
  //自己定义，数字越大，代表优先级越高
  // + 1 => 0 *[] /[] => 1
  def priority(oper: Int): Int = {
    if (oper == '*' || oper == '/') {
      return 1
    } else if (oper == '+' || oper == '-') {
      return 0
    } else {
      -1 //不正确
    }
  }

  def isOper(value: Int): Boolean = {
    value == '+' || value == '-' || value == '/' || value == '*'
  }

  //计算方法--通过两个值和oper进行计算
  def cal(num1: Int, num2: Int, oper: Int): Int = {
    var res = 0
    oper match {
      case '+' => {
        res = num1 + num2
      }
      case '-' => {
        res = num2 - num1
      }
      case '*' => {
        res = num1 * num2
      }
      case '/' => {
        res = num2 / num1
      }
    }
    res
  }
}