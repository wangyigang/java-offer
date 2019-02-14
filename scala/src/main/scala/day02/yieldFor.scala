package day02

object yieldFor {
  def main(args: Array[String]): Unit = {
//    test01()
//    test02()
//    test03()
    //test04()
    //test05()
//    test06()
    test07()
  }
  def test07(): Unit ={
    //100以内的数求和，求出当和 第一次大于20的当前数是多少
    val  max =100
    var sum =0
    var flag=true
    for (i<- 1 to max if flag==true){
      sum+=i
      if (sum>20){
        println("当前i="+i)
        flag=false
      }
    }
  }

  def test06(): Unit ={
    //统计1——200之间能被5整除但不能被3整除的个数
    var n=1
    var max=200
    var count =0
    do {
      if (n%5==0 && n%3!=0){
        count+=1
      }
      n+=1 //while循环变化条件
    }while(n<=max)
    println(count)
  }


  def test05(): Unit ={
    for (i <- 1 to 10 if i % 3 == 1 ) {
      println("i=" + i)
    }
  }
  def test04(): Unit ={
    for (i<- Range(1,10,3)){
      println(i) //1 4 7
    }
  }
  def test03(): Unit ={
    for {i<- 1 to 3
         j=i*2}{
      println("i="+i+"j="+j)
    }
  }
  def test02(): Unit ={
    //将1到10数中的奇数放入到新的集合
    val res2 = for (i <- 1 to 10) yield  {
      if (i % 2 == 1) {
        i
      }else {
        ()
      }
    }
    println("res2=" + res2)
  }
  def test01(): Unit ={
    val res = for(i <- 1 to 10) yield i * 2
    println(res)
  }

}
