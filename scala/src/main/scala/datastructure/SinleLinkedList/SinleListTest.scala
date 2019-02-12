package datastructure.SinleLinkedList

import scala.util.control.Breaks._

object SinleListTest {
  def main(args: Array[String]): Unit = {
    //    test1()
    test2()
  }

  /**
    * 测试用例1
    */
  def test1(): Unit = {
    val list = new SingleLinkedList()
    val node1 = new HeroNode(1, "胖迪1", "女神")
    val node2 = new HeroNode(2, "胖迪2", "女神")
    val node3 = new HeroNode(3, "胖迪3", "女神")
    list.list()
    list.add(node1)
    list.add(node2)
    list.add(node3)
    list.list()
    println("=========update===========")
    var node4 = new HeroNode(3, "迪丽热巴", "女神")
    list.update(node4)
    list.list()
    println("========delete1=========")
    list.del(3)
    list.list()
    println("=====delete2======")
    list.del(1)
    list.list()

    println("=====sortedAdd=======")

    list.addSorted(node2)
    list.addSorted(node1)
    list.list()
  }

  /**
    * 测试用例2
    */
  def test2(): Unit = {
    val hero1 = new HeroNode(1, "宋江", "及时雨")
    val hero2 = new HeroNode(3, "宋江3", "及时雨3")
    val hero3 = new HeroNode(4, "宋江4", "及时雨4")
    val hero4 = new HeroNode(2, "宋江2", "及时雨2")
    //创建一个单向链表
    val singleLinkedList = new SingleLinkedList
    //    singleLinkedList.add(hero1)
    //    singleLinkedList.add(hero2)
    //    singleLinkedList.add(hero3)
    //    singleLinkedList.add(hero4)

    singleLinkedList.addSorted(hero1)
    singleLinkedList.addSorted(hero2)
    singleLinkedList.addSorted(hero3)
    singleLinkedList.addSorted(hero4)
    singleLinkedList.list()

    //    val hero5 = new HeroNode(3, "吴用", "智多星")
    //    singleLinkedList.update(hero5)
    //    println("==========================")
    //    singleLinkedList.list()
    //
    //    println("@@@@@@@@@@测试删除@@@@@@@@@@@")
    //    singleLinkedList.del(4)
    //    singleLinkedList.del(2)
    //    singleLinkedList.del(1)
    //    singleLinkedList.list()
  }
}

/**
  * 带有头结点的单链表
  * list()遍历时，输出后，将指针指向下一个节点
  * 记得在while循环中添加循环改变条件，防止死循环，一定要在while循环后添加条件
  */
class SingleLinkedList {
  //因为是带有头结点方式的，所以初始化一个头结点
  private val head = new HeroNode(0, "", "")

  //增
  /**
    * 第一种添加方法：
    *
    * @param heroNode
    */
  def add(heroNode: HeroNode): Unit = {
    //利用一个临时节点进行查找
    var tmp = head
    while (tmp.next != null) {
      tmp = tmp.next
    }
    //找到尾节点
    tmp.next = heroNode
  }

  /**
    * 添加元素：以no进行排序方式，升序，从小到大
    *
    * @param heroNode
    */
  //  def addSorted(heroNode: HeroNode): Unit = {
  //    var tmp = head
  //    var foundFlag = false
  //    //如果有相同的，添加失败
  //    breakable {
  //      while (tmp.next != null) { //
  //        if (tmp.next.no > heroNode.no) {
  //
  //          //找到合适位置后添
  //          heroNode.next = tmp.next
  //          tmp.next = heroNode
  //          break()
  //        } else if (tmp.next.no == heroNode.no) {
  //          foundFlag = true
  //          break()
  //        }
  //        tmp = tmp.next
  //      }
  //    }
  //
  //    if (foundFlag) {
  //      printf("待插入的英雄编号 %d 已经有了，不能加入\n", heroNode.no)
  //    }
  //  }

  def addSorted(heroNode: HeroNode): Unit = {

    //因为头结点不能动, 因此我们需要哟有一个临时结点，作为辅助
    //注意，我们在找这个添加位置时，是将这个节点加入到temp的后面
    //因此，在比较时，是将当前的heroNode 和 temp.next 比较
    var temp = head
    var flag = false // flag 是用于判断是否该英雄的编号已经存在, 默认为false
    breakable {
      while (true) {
        if (temp.next == null) { //说明temp已经是链表最后
          break()
        }
        if (temp.next.no > heroNode.no) {
          //位置找到，当前这个节点，应当加入到temp后
          break()
        } else if (temp.next.no == heroNode.no) {
          //已经有这个节点
          flag = true
          break()
        }
        temp = temp.next // 注意
      }
    }
    if (flag) { // 不可以加入
      printf("待插入的英雄编号 %d 已经有了，不能加入\n", heroNode.no)
    } else {
      //加入，注意顺序
      heroNode.next = temp.next
      temp.next = heroNode
    }
  }

  // 删
  /**
    * 根据no进行删除，删除相同的no，需要找到将要删除的单链表节点的前一个节点
    *
    * @param no
    */
  def del(no: Int): Unit = {
    //防御性编程
    if (head.next == null) {
      println("单链表为空,无元素删除...")
      return
    }
    var foundFlag = false;
    var tmp = head
    breakable {
      while (tmp.next != null) { //遍历

        if (tmp.next.no == no) {
          foundFlag = true;
          tmp.next = tmp.next.next
          break()
        }
        tmp = tmp.next
      }
    }
    if (!foundFlag) {
      //没有找到
      println("没有找到...")
    }
  }

  // 改
  /**
    * 修改节点信息
    *
    * @param heroNode
    */
  def update(heroNode: HeroNode): Unit = {
    if (head.next == null) {
      println("单链表为空....")
      return
    }
    var tmp = head.next
    var foundFlag: Boolean = false
    //进行修改数据
    while (tmp != null) {
      //遍历
      if (tmp.no == heroNode.no) {
        foundFlag = true //将变量置为true
        //两者的no号码相同，进行修改
        tmp.name = heroNode.name //将两者数据进行同步
        tmp.nickName = heroNode.nickName
      }
      tmp = tmp.next
    }
    //如果没有找到，输出没找到相同的
    if (!foundFlag) {
      println("未找到相同的人物...")
    }
  }


  // 查
  def list(): Unit = {
    if (head.next == null) {
      println("链表为空,无元素...")
      return
    }
    var tmp = head.next //直接跳过头节点，指向真正的数据
    while (tmp != null) {
      println(tmp)
      tmp = tmp.next //遍历输出后，将指针指向下一个
    }
  }


}

class HeroNode(hereNo: Int, heroName: String, heroNickName: String) {
  var no = hereNo
  var name = heroName
  var nickName = heroNickName

  var next: HeroNode = null //next默认为null
  override def toString: String = no + "\t" + name + "\t" + nickName
}