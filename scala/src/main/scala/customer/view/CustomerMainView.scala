package customer.view

import customer.bean.Customer
import customer.service.CustomerService

import scala.io.StdIn
import util.control.Breaks._

class CustomerMainView {

  var key = ' '
  var loop: Boolean = true
  var customerService: CustomerService = new CustomerService

  def mainView(): Unit = {

    //显示主菜单和前面的项目一样
    //-----------------客户信息管理软件-----------------
    //
    //    1 添 加 客 户
    //    2 修 改 客 户
    //    3 删 除 客 户
    //    4 客 户 列 表
    //    5 退           出
    //
    //    请选择(1-5)：_
    do {

      println("-----------------客户信息管理软件-----------------")
      println("                 1 添 加 客 户")
      println("                 2 修 改 客 户")
      println("                 3 删 除 客 户")
      println("                 4 客 户 列 表")
      println("                 5 退       出")
      println("请选择(1-5):")
      key = StdIn.readChar()

      key match {
        case '1' => this.add
        case '2' => this.update
        case '3' => this.del
        case '4' => this.showList
        case '5' => this.loop = false
        case _ => println("输入错误，请重新输入")

      }

    } while (loop)

    println("你已经成功的退出了系统....");


  }

  //增加customer
  def add(): Unit = {
    //    ---------------------添加客户---------------------
    //    姓名：张三
    //    性别：男
    //    年龄：30
    //    电话：010-56253825
    //    邮箱：zhang@abc.com
    //    ---------------------添加完成---------------------
    println("---------------------添加客户---------------------")
    println("姓名：")
    var name = StdIn.readLine() // 读取一行，获取字符串
    println("性别：")
    var sex = StdIn.readChar() //获取一个char
    println("年龄：")
    var age = StdIn.readInt() //获取一个int
    println("电话：")
    var phone = StdIn.readLine() // 获取一行
    println("邮箱：")
    var email = StdIn.readLine() // 获取一行--字符串类型
    println("---------------------添加完成---------------------")
    //封装成一个customer
    val customer = new Customer(age, name, sex, phone, email)
    customerService.add(customer)

  }

  //删除customer
  def del(): Unit = {
    //    ---------------------删除客户---------------------
    //    请选择待删除客户编号(-1退出)：1
    //    确认是否删除(Y/N)：y
    //    ---------------------删除完成---------------------
    println("---------------------删除客户---------------------")
    println("请选择待删除客户编号(-1退出)：")
    var id = StdIn.readInt()
    if (id == -1) {
      return
    }

    var ch = ' '
    breakable {
      do {
        println("确认是否删除(Y/N)：")
        ch = StdIn.readChar().toUpper //转为大写--1.toUpper转为大写，可以少些一半小写的情况
        if (ch != 'Y || ch != 'N') { //都可以，随意
          break()
        }
      } while (true)
    }

    if (ch == 'Y') {
      //输入的是id
      if (this.customerService.del(id)) {
        println("------------------删除完成-------------------")
      } else {
        println("-----------------删除未完成-------------------")
      }
    } else {
      println("------------------放弃删除-------------------")
    }

  }

  //显示所有的customer
  def showList(): Unit = {
    //    ---------------------------客户列表---------------------------
    //    编号  姓名       性别    年龄   电话            邮箱
    //      1    张三       男      30     010-56253825   abc@email.com
    //    2    李四       女      23     010-56253825    lisi@ibm.com
    //    3    王芳       女      26     010-56253825   wang@163.com
    //    -------------------------客户列表完成-------------------------

    println("---------------------------客户列表---------------------------")
    println("编号\t\t姓名\t\t性别\t\t年龄\t\t电话\t\t邮箱")
    val list = customerService.showAll()
    for (item <- list) {
      println(item)
    }
    println("-------------------------客户列表完成-------------------------")
  }

  //修改customer方法
  def update(): Unit = {
    //    ---------------------修改客户---------------------
    //    请选择待修改客户编号(-1退出)：1
    //    姓名(张三)：<直接回车表示不修改>
    //    性别(男)：
    //    年龄(30)：
    //    电话(010-56253825)：
    //    邮箱(zhang@abc.com)：zsan@abc.com
    //    ---------------------修改完成---------------------
    println("---------------------修改客户---------------------")
    println("   请选择待修改客户编号(-1退出)：")
    val id = StdIn.readInt()//获取用户输入的id号

    val customer = customerService.findById(id)
    if (customer == null){
      println("输入错误，不存在")
    }
    var nId = customer.id
    println(s"姓名(${customer.name})：<直接回车表示不修改>")
    val nName = StdIn.readLine()
    println(s"性别(${customer.gender})：")
    var nGender = StdIn.readChar()
    println(s" 年龄(${customer.age})：")
    var nAge= StdIn.readInt()
    println(s"  电话(${customer.phone})")
    var nPhone = StdIn.readLine()
    println(s" 邮箱(${customer.email})：")
    var nEmail = StdIn.readLine()
    var nCustomer = new Customer(nId,nName,nGender,nPhone,nEmail)
    customerService.update(nCustomer)
    println("---------------------修改完成---------------------")

  }

}
