package datastructure.Recursive

/**
  * 迷宫问题
  */
object RecursiveTest {
  def main(args: Array[String]): Unit = {
    test01()
  }

  def test01(): Unit = {
    //创建二维数组模仿迷宫--上下左右四周为数字1，表示迷宫
    val array = Array.ofDim[Int](8, 7)
    //上下 全部置为1
    for (i <- 0 to 6){
      array(0)(i) = 1
      array(7)(i) =1
    }
    //左右全部置为1
    for (i<- 0 to 7){
      array(i)(0)=1
      array(i)(6) =1
    }

    //设置两个格挡
    array(3)(1) = 1
    array(3)(2) = 1

    //打印地图
    for (i <- 0 until 8) {
      for (j <- 0 until 7) {
        print(array(i)(j) + " ")
      }
      println()
    }
    println("================")
    // 参数：数组，起始坐标
    setWay(array, 1,1)

    //查找结束后，再次进行打印地图
    for (i <- 0 until 8) {
      for (j <- 0 until 7) {
        print(array(i)(j) + " ")
      }
      println()
    }
  }

  /**
    * 定义一个策略：
    * 数字0表示可以走，还没走
    * 数字1表示墙
    * 数字2表示可以走
    * 数字3表示已经走过，死路
    * 确定一个行走策略： 下=>右=>上=》左
    * 其实位置从(1,1)开始
    */
  def setWay(array: Array[Array[Int]], i:Int,j:Int): Boolean = {
    //设置递归结束条件
    if (array(6)(5) ==2){
      return true
    }
    if (array(i)(j) ==0){
      array(i)(j)=2 //立刻置为2，表示可以走的路
      //进行递归策略
      //下
      if (setWay(array,i+1,j)){
        return true
      }else if (setWay(array,i,j+1)){
        //右
        return true
      }else if (setWay(array,i-1,j)){
        //上
        return true
      }else if (setWay(array,i,j-1)){
        //左
        return true
      }else{ //上下左右不都行，
        return false;
      }
    }else{ //非0 1,2,3
      return false
    }
  }
}
