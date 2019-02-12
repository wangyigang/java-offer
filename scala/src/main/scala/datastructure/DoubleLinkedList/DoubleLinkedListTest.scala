package datastructure.DoubleLinkedList

import  scala.util.control.Breaks._
object DoubleLinkedListTest {
  def main(args: Array[String]): Unit = {

  }
}

class DoubleLinkedList{
  //先初始化一个头结点
  val head= new DoubleListNode(0,"")
  def add(heroNode: DoubleListNode): Unit = {
    //因为头结点不能动, 因此我们需要哟有一个临时结点，作为辅助
    var temp = head
    //找到链表的最后
    breakable {
      while (true) {
        if (temp.next == null) { //说明temp已经是链表最后
          break()
        }
        //如果没有到最后
        temp = temp.next
      }
    }
    //当退出while循环后，temp就是链表的最后
    temp.next = heroNode
    heroNode.prev = temp

  }

  //遍历方法一样, 可以直接使用单链表的，
  def list(): Unit = {

    //判断当前链表是否为空
    if (head.next == null) {
      println("链表为空!!")
      return
    }
    //因为头结点不能动, 因此我们需要哟有一个临时结点，作为辅助
    //因为head 结点数据，我们不关心，因此这里 temp=head.next
    var temp = head.next
    breakable {
      while (true) {
        //判断是否到最后
        if (temp == null) {
          break()
        }
        printf("结点信息 no=%d name=%s \n",
          temp.no, temp.name)
        temp = temp.next
      }
    }
  }

  //更新无更改--可以使用单链表的
  def update(newHeroNode: DoubleListNode): Unit = {
    if (head.next == null) {
      println("链表为空")
      return
    }
    //先找到节点
    var temp = head.next
    var flag = false
    breakable {
      while (true) {
        if (temp == null) {
          break()
        }
        if (temp.no == newHeroNode.no) {
          //找到.
          flag = true
          break()
        }
        temp = temp.next //
      }
    }
    //判断是否找到
    if (flag) {
      temp.name = newHeroNode.name
    } else {
      printf("没有找到 编号为%d 节点，不能修改\n", newHeroNode.no)
    }

  }

  //删除
  //思路，因为双向链表可以实现自我删除
  //双向链表可以实现自我删除，所以需要重新实现
  def del(no: Int): Unit = {

    //判断当前链表是否为空
    if (head.next == null) {
      println("链表空")
      return
    }

    var temp = head.next
    var flag = false // 标志变量用于确定是否有要删除的节点
    breakable {
      while (true) {
        if (temp == null) {
          break()
        }
        if (temp.no == no) {
          //找到了
          flag = true
          break()
        }
        temp = temp.next //temp后移
      }
    }

    if (flag) {
      //可以删除
      //temp.next = temp.next.next
      temp.prev.next = temp.next
      //思考
      if (temp.next != null) {
        temp.next.prev = temp.prev
      }
    } else {
      printf("要删除的no=%d 不存在\n" , no)
    }
  }

}

//双向链表节点
class DoubleListNode(hereNo:Int, heroName:String){
  var no :Int = hereNo
  var name = heroName
  var prev :DoubleListNode = null; //默认置为空
  var next:DoubleListNode = null; //默认置为空
}