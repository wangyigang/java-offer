package day03

object AbstractTest {
  def main(args: Array[String]): Unit = {

  }
}

class Account(iAccountNo:String, iPwd:Int, iBalance:Double){
  //属性
  var accountNo:String = iAccountNo
  private var pwd:Int = iPwd
  private var balance :Double = iBalance

  //查询账户
  def query(pwd:Int): Unit ={
    if(pwd != this.pwd){
      println("密码不正确..")
      return
    }
  }
}
