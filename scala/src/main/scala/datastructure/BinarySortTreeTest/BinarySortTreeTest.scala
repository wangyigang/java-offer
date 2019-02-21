package datastructure.BinarySortTreeTest

object BinarySortTreeTest {
  def main(args: Array[String]): Unit = {
    test01()
  }

  def test01(): Unit = {
    //测试一把
    val arr = Array(7, 3, 10, 12, 5, 1, 9, 2)
    //创建一颗二叉排序树
    val binarySortTree = new BinarySortTree
    for (item <- arr) {
      binarySortTree.add(new listNode(item))
    }
    //遍历二叉排序树
    binarySortTree.midOrder() // 1,3,5,7,9,10,12


    //删除
    binarySortTree.delNode(2)
    binarySortTree.delNode(5)
    binarySortTree.delNode(9)
    binarySortTree.delNode(12)

    binarySortTree.delNode(10)
    binarySortTree.delNode(1)
    binarySortTree.delNode(3)
    binarySortTree.delNode(7)


    println("\n=======删除========")
    binarySortTree.midOrder()
  }
}

//node节点
class listNode(var no: Int) {

  var left: listNode = null
  var right: listNode = null



  /**
    * 找到删除节点
    *
    * @param no
    * @return
    */
  def findDelNode(no: Int): listNode = {
    if (this.no == no) {
      //找到
      return this
    } else if (no < this.no) {
      if (this.left == null) {
        return null //找到左子树尽头也没有知道到
      } else {
        return this.left.findDelNode(no)
      }
    } else {
      if (this.right == null) {
        return null //没有找到
      } else {
        return this.right.findDelNode(no)
      }
    }
    null
  }

  /**
    * 找到删除节点的父节点
    *
    * @param no
    * @return
    */
  def findDelParentNode(no: Int): listNode = {
    //思路：判断组
    //思路
    //1. 先判断当前节点的左子节点或者右子节点是否是这个值
    if ((this.left != null && this.left.no == no) ||
      (this.right != null && this.right.no == no)) {
      return this
    } else {
      if (this.left != null && no < this.no) { //说明需要向左边去递归查找
        return this.left.findDelParentNode(no)
      } else if (this.right != null && no > this.no) { //说明需要向右边去递归查找
        return this.right.findDelParentNode(no)
      } else {
        null
      }
    }
  }


  //中序遍历
  /**
    * 节点的中序遍历
    */
  def midOrder(): Unit = {
    //左
    if (this.left != null) {
      this.left.midOrder()
    }
    // 中
    print(no + " ")
    // 右
    if (this.right != null) {
      this.right.midOrder()
    }
  }

  //add
  /**
    * 真正添加的地方
    *
    * @param node
    */
  def add(node: listNode): Unit = {
    //左子树
    if (this.no > node.no) { //要插入的节点比当前节点小，找左子树
      if (this.left == null) {
        this.left = node
      } else {
        this.left.add(node)
      }
    } else if (this.no < node.no) {
      //右子树
      if (this.right == null) { //到达尽头节点
        this.right = node
      } else {
        this.right.add(node)
      }
    } else { //相等情况
      return
    }
  }

}


class BinarySortTree {

  var root: listNode = null

  //添加
  def add(node: listNode) = {
    if (root == null) {
      root = node
    } else {
      //不是根节点吗，进行二叉排序插入
      root.add(node)
    }
  }

  //遍历--中序遍历，中序遍历的输出会根据大小顺序进行输出
  def midOrder(): Unit = {
    if (root == null) {
      println("当前二叉树为空,无法遍历...")
      return
    }
    root.midOrder()
  }

  def delRightTreeMin(node:listNode): Int = {
    var target = node
    //使用while 循环找到右子树的最小值
    while (target.left != null){
      target = target.left
    }
    val minValue = target.no
    //删除最小值对应的节点
    delNode(minValue)
    return minValue
  }

  /**
    * 删除一个节点
    * 如果想要删除节点，需要考虑三种情况
    *  1. 删除节点为叶子节点 ：找到父节点，直接删除
    *  2.删除节点为只有单个子节点:找到删除节点的父节点链向子节点
    *  3.删除节点有两个子节点： 从右子树中找到最小值，然后删除最小节点，并替换最小节点的值和删除节点值
    *
    * @param no
    */
  def delNode(no: Int):Unit = {
    if (root == null) {
      println("当前为空，无法删除...")
    }
    //先看有没有要删除节点
    var targetNode = findDelNode(no)
    if (targetNode == null) {
      return
    }
    var parentNode = FindDelParentNode(no)
    if (parentNode == null) {
      if (targetNode == root){
        root =null
      }
      targetNode = null
      return
    }
    //进行三种情况的判断
    //第一种情况,
    if (targetNode.left == null && targetNode.right == null) {
      //判断删除的节点是parentNode的左子节点，还是右子节点
      if (parentNode.left != null && parentNode.left.no == no) {
        parentNode.left = null
      } else {
        parentNode.right = null
      }
    } else if (targetNode.left != null && targetNode.right != null) { // targetNode只有两个子节点
      val value = delRightTreeMin(targetNode.right)
      targetNode.no = value

    } else { //只有targetNode只有一个子节点
      //判断targetNode 是parentNode 的左子节点还是右子节点
      if (targetNode.left != null) { //要删除的节点的左子节点不为空
        //判断targetNode 是parentNode 的左还是右
        if (parentNode.left.no == no) {
          parentNode.left = targetNode.left
        } else {
          parentNode.right = targetNode.left
        }
      } else {
        //判断targetNode 是parentNode 的左还是右
        if (parentNode.left.no == no) {
          parentNode.left = targetNode.right
        } else {
          parentNode.right = targetNode.right
        }
      }

    }
  }


  //查找节点
  def findDelNode(value: Int): listNode = {
    if (root != null) {
      return root.findDelNode(value)
    } else {
      return null
    }
  }

  //查找父节点的方法
  def FindDelParentNode(value: Int): listNode = {
    if (root != null) {
      return root.findDelParentNode(value)
    } else {
      return null
    }
  }
}
