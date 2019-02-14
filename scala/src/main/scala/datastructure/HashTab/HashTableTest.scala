package datastructure.HashTab


import scala.io.StdIn
import util.control.Breaks._

object HashTabDemo {
  def main(args: Array[String]): Unit = {

    //创建HashTab
    val hashTab = new HashTab(2)
    //写一个简单菜单
    var key = " "
    while (true) {
      println("add:  添加雇员")
      println("list: 显示雇员")
      println("find: 查找雇员")
      println("exit: 退出系统")

      key = StdIn.readLine()
      key match {
        case "add" => {
          println("输入id")
          val id = StdIn.readInt()
          println("输入名字")
          val name = StdIn.readLine()
          val emp = new Emp(id, name)
          hashTab.add(emp)
        }
        case "find" => {
          println("输入要查找的雇员的id")
          val id = StdIn.readInt()
          hashTab.findEmpById(id)
        }
        case "list" => {
          hashTab.list()
        }
      }

    }
  }
}

//创建Emp类
class Emp(eId: Int, eName: String) {
  val id = eId
  var name = eName
  var next: Emp = null
}

//创建EmpLinkedList
class EmpLinkedList {
  //定义头指针, 这里head 我们直接回指向一个雇员
  var head: Emp = null

  //添加雇员方法
  //假定，添加的雇员的id是自增的，即雇员分配的id总是从小到大
  //找到链表的最后加入即可
  def add(emp: Emp): Unit = {

    //如果是第一个雇员
    if (head == null) {
      head = emp
      return
    }
    //定义辅助指针
    var cur = head

    breakable {
      while (true) {
        if (cur.next == null) {
          break()
        }
        cur = cur.next
      }
    }
    //这时cur 指向了链表的最后
    cur.next = emp

  }

  def addSorted(emp: Emp): Unit = {
    if (head == null) {
      head = emp
      return
    }

    var temp = head

    var prev = new Emp(-1, "")
    prev.next = temp
    var findFlag = false
    var existFlag = false // flag 是用于判断是否该编号已经存在, 默认为false
    breakable {
      while (true) {
        if (temp == null) {
          break()
        }
        if (temp.id > emp.id) {
          findFlag = true
          //找到位置
          break()
        } else if (temp.id == emp.id) {
          //重复
          existFlag = true
          break()
        }
        prev = temp
        temp = temp.next
      }
    }

    if (existFlag) { // 不可以加入
      printf("待插入的id %d 已经有了，不能加入\n", emp.id)
    } else {
      //加入，注意顺序
      if (findFlag) {// 需要在非尾部插入
        emp.next = temp
        prev.next = emp
        head = emp
      } else { //需要在尾部插入，prev跟随者temp进行移动
        prev.next = emp
      }

    }
  }

  //遍历链表的方法
  def list(i: Int): Unit = {
    if (head == null) {
      println(s"第${i}条链表为空")
      return
    }

    print(s"第${i}条链表信息为\t")
    //定义辅助指针
    var cur = head
    breakable {
      while (true) {
        if (cur == null) {
          break()
        }
        //输出雇员信息
        printf(" => id=%d name=%s\t", cur.id, cur.name)
        cur = cur.next //
      }
    }
    println()
  }

  //如果有，返回emp ,没有返回null
  def findEmpById(id: Int): Emp = {
    //遍历
    if (head == null) {
      println("链表为空，没有数据~~")
      return null
    }

    var cur = head

    breakable {
      while (true) {
        if (cur == null) {
          break()
        }
        if (cur.id == id) {
          break()
        }
        cur = cur.next
      }
    }
    return cur
  }
}


class HashTab(val size: Int) { //size 会称为只读属性
  val empLinkedListArr: Array[EmpLinkedList] = new Array[EmpLinkedList](size)
  //初始化我们的empLinkedListArr 的各个元素
  for (i <- 0 until size) {
    empLinkedListArr(i) = new EmpLinkedList
  }

  def add(emp: Emp): Unit = {
    //返回该员工，应该加入到那条链表
    val empLinkedListNo = hashFun(emp.id)
    //排序方式
    empLinkedListArr(empLinkedListNo).addSorted(emp)
  }

  def list(): Unit = { //遍历整个hash表
    for (i <- 0 until size) {
      empLinkedListArr(i).list(i)
    }
  }

  //编写一个findEmpById
  def findEmpById(id: Int): Unit = {
    //返回该员工，应该加入到那条链表
    val empLinkedListNo = hashFun(id)
    val emp = this.empLinkedListArr(empLinkedListNo).findEmpById(id)
    if (emp != null) {
      printf(s"在第 $empLinkedListNo 找到id=%d name=%s\n", id, emp.name)
    } else {
      printf("没有找到id为 %d \n", id)
    }
  }

  //散列函数, 可以定制
  def hashFun(id: Int): Int = {
    id % size
  }
}