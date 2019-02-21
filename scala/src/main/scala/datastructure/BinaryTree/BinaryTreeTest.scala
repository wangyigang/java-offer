package datastructure.BinaryTree

/**
  * 二叉树的前中后遍历
  * 二叉树的
  */
object BinaryTreeTest {
  def main(args: Array[String]): Unit = {
//    test01()
    test02()
  }
  //二叉树的搜索
  def test02(): Unit ={

  }
  def test01(): Unit = {
    //先使用比较简单方法，直接关联的方法
    val root = new ListNode(1, "11")
    val hero2 = new ListNode(2, "22")
    val hero3 = new ListNode(3, "33")
    val hero4 = new ListNode(4, "44")
    val hero5 = new ListNode(5, "55")

    root.left = hero2
    root.right = hero3

    hero3.left = hero4
    hero3.right = hero5

    val binaryTree = new BinaryTree
    binaryTree.root = root
    println("======前序遍历======")
    binaryTree.prevOrder()
    println("=======中序遍历======")
    binaryTree.midOrder()
    println("=======后序遍历======")
    println("后序遍历")
    binaryTree.postOrder()
  }
}


class ListNode(listNo: Int, listName: String) {


  var no = listNo
  var name = listName
  //左指针
  var left: ListNode = null //如果初始值为null,一定要指定类型
  var right: ListNode = null //右节点初始为null

  //前序遍历
  def prevOrder(): Unit = {
    //前序遍历,先输出当前节点值
    println("no:" + this.no + " name:" + this.name)
    if (this.left != null) {
      this.left.prevOrder()
    }
    if (this.right != null) {
      this.right.prevOrder()
    }
  }

  /**
    * 前序搜索，先搜索再左右
    * @param no
    * @return
    */
  def prevSearch(no:Int): ListNode={
    var res:ListNode = null
    if (no==this.no){
      return this
    }
    //左
    if (this.left!=null){
      res = this.left.prevSearch(no)

    }
    if (res!=null){
      return res
    }
    //右
    if (this.right!=null){
      res = this.right.prevSearch(no)
    }
    //直接返回res即可，不论res是否是null，直接返回
    res
    //    if (res!=null){
    //      return res
    //    }
    //    return null
  }

  //中序遍历
  def midOrder(): Unit = {
    //中序,左中右
    if (this.left != null) {
      this.left.midOrder()
    }
    //中序输出
    println("no:" + this.no + " name:" + this.name)
    if (this.right != null) {
      this.right.midOrder()
    }
  }

  /**
    * 中序搜索no
    * @param no
    * @return
    */
  def midSearch(no: Int): ListNode = {
    var res :ListNode = null
    if (this.left != null){
      res = this.left.midSearch(no)
    }
    //中序查找
    if (res != null){
      return res
    }
    if (this.no == no){
      return this
    }
    if (this.right!= null){
      res = this.right.midSearch(no)
    }
    return res
  }


  //后序遍历
  def postOrder(): Unit = {
    if (this.left != null) {
      this.left.postOrder()
    }
    if (this.right != null) {
      this.right.postOrder()
    }
    //后序遍历
    println("no:" + this.no + " name:" + this.name)
  }

  //后序查找
  def postSearch(no: Int): ListNode = {
    var res:ListNode= null
    //左
    if (this.left!= null){
      res = this.left.prevSearch(no)
    }
    if (res != null){
      return res
    }
    //右
    if (this.right!= null){
      res = this.right.postSearch(no)
    }
    if (res!= null){
      return res
    }
    //根
    if (this.no == no){
      return this
    }
    return null
  }
}

class BinaryTree {
  //根节点
  var root: ListNode = null //初始为null

  //后序遍历
  def postOrder(): Unit = {
    if (root == null) {
      println("当前二叉树为空，不能遍历...")
      return
    } else {
      root.postOrder()
    }
  }
  //后序查找
  def postSearch(no:Int):ListNode={
    if (root == null){
      println("当前二叉树为空, 不能搜索..")
      return null
    }else{
      root.postSearch(no)
    }
  }

  //中序遍历
  def midOrder(): Unit = {
    if (root == null) {
      println("当前二叉树为空，不能遍历...")
      return
    } else {
      root.midOrder()
    }
  }
  //中序查找
  def midSearch(no:Int):ListNode={
    if(root==null){
      println("当前二叉树为空，不能搜索...")
      return  null
    }else{
      root.midSearch(no)
    }
  }

  //前序遍历
  def prevOrder(): Unit = {
    if (root == null) {
      println("当前二叉树为空，不能遍历...")
      return
    } else {
      root.prevOrder()
    }
  }
  //前序查找
  def preOrderSearch(no:Int): ListNode ={
    if (root==null){
      println("当前二叉树为空，不嫩搜索...")
      return null
    }else{
      val node = root.prevSearch(no)
      return node
    }
  }
}

