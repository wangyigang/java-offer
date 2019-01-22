package day06

//控制抽象 简化版--省略():
/*

 */
object AbstractControTest02 {
  def main(args: Array[String]): Unit = {
    //
    def myRunInThread(f1 : =>Unit): Unit ={ //省略()
      new Thread{
        override def run(): Unit = f1  //省略()
      }.start() //启动一个线程
    }

    myRunInThread{ // 函数体 --省略()=>
      println("干活啦! 5秒完成...")
      Thread.sleep(5000)
      println("干完咯!")
    }
  }

}
