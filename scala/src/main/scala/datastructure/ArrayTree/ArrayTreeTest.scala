package datastructure.ArrayTree

object ArrayTreeTest {
  def main(args: Array[String]): Unit = {
    val arr =  Array(1,2,3,4,5,6,7)
    val arrayTree = new ArrayTree(arr)
    //前序遍历我们的数组
//    arrayTree.preOrder() //1,2,4,5,3,6,7

//    //中序遍历
//    arrayTree.midOrder() //4 2 5 1 6 3 7
    //后序遍历
    arrayTree.postOrder()  //4 5 2 6 7 3 1
  }
}

class ArrayTree(val arr:Array[Int]){



  //前序遍历
  def preOrder(): Unit ={
    this.preOrder(0)
  }
  //前序遍历
  def preOrder(index:Int): Unit ={
    if (arr==null || arr.length ==0){
      println("当前数组为空...")
      return
    }
    //前序先输出
    print(arr(index)+" ")
    if ((index*2+1) < arr.length){
      preOrder(index*2+1)
    }
    if ((index*2+2)< arr.length){
      preOrder(index*2+2)
    }
  }
  def midOrder(): Unit ={
    midOrder(0)
  }
  //中序遍历
  def midOrder(no:Int): Unit = {
    if(arr == null || arr.length ==0){
      println("当前数组为空，不能进行二叉树遍历...")
      return
    }
    //左
    if ((no*2+1) < arr.length){
      midOrder(no*2+1)
    }
    //中
    print(arr(no)+" ")
    //右
    if ((no*2+2) < arr.length){
      midOrder(no*2+2)
    }

  }
  //后序遍历
  def postOrder(): Unit = {
    postOrder(0)
  }
  //后序遍历
  def postOrder(index:Int): Unit ={
    //左
    if ((2*index+1) < arr.length){
      postOrder(2*index+1)
    }
    //右
    if ((2*index+2) < arr.length){
      postOrder(2*index+2)
    }
    //中
    print(arr(index)+" ")
  }
}

