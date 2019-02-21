package datastructure.Calculator

import util.control.Breaks._

/**
  * 用栈计算表达式[7*2*2-5+1-5+3-3]
  *
  * 使用两个栈的方式，一个数字栈，一个符号栈
  */
object CalculatorTest {
  def main(args: Array[String]): Unit = {
    val expression = "7*2*2-5+1-5+3-4"
    var numStack= new ArrayStack(10)
    var operStack= new ArrayStack(10)


    var index =0
    var num1 =0
    var num2 =0
    var oper =0
    var res =0
    var ch =' '
    var keepNum =""

    //设计两个栈，树栈和符号栈
    //一个一个的去除char
    //判断当前去除的字符是符号时，直接入栈，
    //会循环的取出expression 字符
    breakable {
      while (true) {

        //扫描expression
        ch = expression.substring(index, index + 1)(0)

        if (operStack.isOper(ch)) { //如果是操作符..

          if (!operStack.isEmpty()) {
            //如果当前符号的优先级 小于等于符号栈的栈顶的符号的优先级，则取出该符号，并从数栈依次 //pop 出两个数据，进行运算，将结果重新puhs到 数栈，再将当前符号push 到符号栈
            if (operStack.priority(ch) <= operStack.priority(operStack.array(operStack.top))) {
              //开始计算
              num1 = numStack.pop().toString.toInt
              num2 = numStack.pop().toString.toInt
              oper = operStack.pop().toString.toInt
              res = numStack.cal(num1, num2, oper)
              //入数字栈
              numStack.push(res)
              //把当前ch入符号栈
              operStack.push(ch)
            } else {
              //如果当前的符号的优先级大于符号栈顶的符号优先级，直接入栈
              operStack.push(ch)
            }
          } else {
            //符号就直接入栈
            operStack.push(ch) // '+' => 43
          }

        } else { // 是数
          //处理多位数的逻辑
          keepNum += ch

          //如果ch 已经是expression 最后一个字符
          if (index == expression.length - 1) {
            numStack.push(keepNum.toInt)
          }else {

            //判断ch 的下一个字符是不是数字, 如果是数字，则进行一次扫描，如果是操作符，就直接入栈
            //看到expresson的下一个字符时，不要真正的移动index ,只是探测一下
            if (operStack.isOper(expression.substring(index + 1, index + 2)(0))) {
              //是操作符入栈
              numStack.push(keepNum.toInt)
              keepNum = "" // 清空
            }
          }

          //numStack.push((ch + "").toInt) // ? '1' => 49 '3' "1"=> 1
        }

        //index 后移
        index += 1
        //判断是否到表达式的最后
        if (index >= expression.length()) {
          break()
        }

      }
    }

    //当整个表达式扫描完毕后，依次从数栈和符号栈取出数据，进行运行，最后在数栈中的数据就是结果
    breakable {
      while (true) {
        if (operStack.isEmpty()) {
          break()
        }
        //运算
        //开始计算
        num1 = numStack.pop().toString.toInt
        num2 = numStack.pop().toString.toInt
        oper = operStack.pop().toString.toInt
        res = numStack.cal(num2, num1, oper)
        numStack.push(res) //入栈
      }
    }

    //将数字栈的最后结果pop
    val res2 = numStack.pop()
    printf("表达式 %s = %d", expression, res2)
  }
}


//还要设计一个栈,用于专门的计算 --有一个专门用于计算的方法
//底层使用array实现栈，有一个记录栈顶的指针

class ArrayStack(maxSize:Int){
  val size = maxSize
  var array = new Array[Int](size) //初始化
  var top = -1  //指向栈顶的指针，初始化为-1

  //判断栈满
  def isFull(): Boolean={
    top == size-1
  }
  //判断栈空
  def isEmpty():Boolean={
    top == -1
  }
  //push
  def push(num:Int): Unit ={
    if (isFull()){
      println("栈已满，无法添加...")
      return
    }
    //正常情况，添加数据
    top+=1 //先将top改变，top默认指向-1
    array(top) = num
  }
  //pop
  def pop():Int={
    if (isEmpty()){
      println("栈空...")
      return -1
    }
    var value= array(top)
    top-=1  //top栈顶指针向下移动一个单位
    value //返回value
  }
  //遍历list
  /**
    * 遍历
    */
  def list(): Unit ={

    for (i <- 0 to top reverse){
      println("栈id="+i+"元素="+array(i))
    }
  }
  //计算 num1 num2 oper--获取计算结果
  def cal(num1:Int, num2:Int, oper:Int): Int ={
    oper match {
      case '+' =>{
          num1+num2
      }
      case '-' =>{
        num1-num2
      }
      case '*'=>{
        num1*num2
      }
      case '/' =>{
        num1/num2
      }
      case _=>{
        0
      }
    }
  }


  //返回运算符的优先级, 是程序员定, 数字越大，优先级越高
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
}



