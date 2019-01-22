package day06

/*
Scala Option(选项)类型用来表示一个值是可选的（有值或无值)。

Option[T] 是一个类型为 T 的可选值的容器： 如果值存在， Option[T]
 就是一个 Some[T] ，如果不存在， Option[T] 就是对象 None 。

 */
object AbstractControTest01 {
  def main(args: Array[String]): Unit = {

    //控制抽象--条件：
    // 参数时函数--函数参数没有输入值也没有返回值-
    def myRunInThread(f1: () => Unit) = { //参数是函数 --函数参数没有输入也没有输出
      new Thread { //创建一个线程
        override def run(): Unit = { //实现run()方法
          f1() //调用传入的函数
        }
      }.start()
    }

    myRunInThread {
      () => //f1函数 =>后面是真正的函数实体
        println("干活咯！5秒完成...")
        Thread.sleep(5000)
        println("干完咯！")
    }
  }
}
