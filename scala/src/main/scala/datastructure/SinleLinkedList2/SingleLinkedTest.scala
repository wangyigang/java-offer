package datastructure.SinleLinkedList2
import util.control.Breaks._

object SingleLinkedTest {
  def main(args: Array[String]): Unit = {
    test1()
  }
  def test1(): Unit = {
    val list = new SingleLinkedList()
    val node1 = new ListNode(1, "胖迪1")
    val node2 = new ListNode(2, "胖迪2")
    val node3 = new ListNode(3, "胖迪3")
    list.list()
    list.addSorted(node1)
    list.addSorted(node3)
    list.addSorted(node2)
    println("======================")
    list.list()
    println("=========update===========")
    var node4 = new ListNode(3, "迪丽热巴")
    list.update(node4) //将3号name改为迪丽热巴
    list.list()
    var node5 = new ListNode(5,"迪丽热巴")
    list.update(node5) //测试no不存在情况

    println("========delete1=========")
    list.delete(3)
    list.delete(1)
    list.delete(2)
    list.list()
    println("=====delete2======")
    list.delete(5) //删除一个不存在节点
    list.list()

  }
}

class SingleLinkedList{
  //头结点
  val head = new ListNode(0, "")

  //add
  /**
    * 添加到尾部，没有进行排序
    * @param node
    */
  def add(node:ListNode): Unit = {
    //使用临时变量进行查找
    var tmp = head
    while(tmp.next != null){
      tmp = tmp.next
    }
    //找到尾节点后
    tmp.next = node
  }

  //addsorted
  /**
    * 重点：链表按照no排序插入
    * @param node
    */
  def addSorted(node:ListNode): Unit ={
    //处理第一次插入情况
    if (head == null){
      head.next = node
      return
    }

    var tmp = head
    //两种情况，一：按照no找到合适的插入位置 2：如果no有重复的返回false,表示已有重复的，错误
    var existFlag = false
    breakable {
      while (true) {
        //处理循环结束条件
        if (tmp.next == null){ //最后一个节点位置
          break()
        }

        //按照no从小到大的位置进行插入
        if (tmp.next.no > node.no) {
          break()
        } else if (tmp.next.no == node.no) {
          existFlag = true
          break()
        }

        //while变化条件
        tmp= tmp.next
      }
    }
    //找到插入位置
    if (existFlag){
      println("节点已存在,无法插入...")
    }else{
      //先处理后面的逻辑链表逻辑
      node.next = tmp.next
      //在处理前面的链表逻辑
      tmp.next = node
    }
  }
  //del //删除节点，根据no进行删除
  def delete(no:Int): Unit ={
    if (head.next == null){
      println("链表为空,无数据...")
      return
    }
    //因为单链表的删除需要找到节点的前一个节点，然后进行改变链表指向关系，所以是head
    var tmp = head //head,不是head.next
    var findFlag= false
    while(tmp!=null && tmp.next != null){
      //找到no相同的
      if (tmp.next.no == no){
        findFlag= true
        tmp.next = tmp.next.next
      }
      tmp = tmp.next
    }
    if (!findFlag){
      println("id="+ no+"的节点不存在...")
    }

  }
  //update--更新
  /**
    * 更新操作
    * @param node
    */
  def update(node:ListNode): Unit = {
    if (head.next == null){
      println("链表为空...")
      return
    }
    var tmp = head.next
    var existFlag = false
    while(tmp!= null){
      //no相同，进行替换
      if(tmp.no == node.no){
        existFlag= true
        tmp.name = node.name
      }
      //while变化条件
      tmp = tmp.next
    }
    if (!existFlag){
      println("节点不存在，不能进行替换...")
    }
  }
  //查看list
  /**
    * 显示所有节点信息
    */
  def list(): Unit ={
    if (head.next ==null){
      println("空链表...")
      return
    }
    var tmp = head.next
    while(tmp!= null){
      println(tmp) //打印tmp的信息
      //while循环变化条件
      tmp=tmp.next
    }
  }


}


class ListNode(hereNo: Int, heroName: String) {
  var no = hereNo
  var name = heroName

  var next: ListNode = null //next默认为null
  override def toString: String = no + "\t" + name  //重写toString方法
}