package customer.service

import customer.bean.Customer

import scala.collection.mutable.ArrayBuffer
import util.control.Breaks._

class CustomerService {
  //更新操作--根据相同id
  def update(nCustomer: Customer) = {

    customers(nCustomer.id) = nCustomer // 通过值进行覆盖--不用进行判断，此时一定存在
  }


  //定义一个arrayBuffer，保存custormer信息
  val customers = ArrayBuffer[Customer](new Customer(1, 10, "tom", '男', "111", "tom@sohu.com"))

  //表示当前的customer的个数
  var totalCustomerNum = 1

  def showAll(): ArrayBuffer[Customer] = {
    customers
  }

  //servicec层用add增加customer
  def add(customer: Customer): Unit = {
    totalCustomerNum += 1 //先进行自增
    customer.id = totalCustomerNum; //然后赋值给id，使id自增1
    customers.append(customer)
  }

  //通过id查找到
  def findById(id: Int): Customer = {
    for (i <- customers) {
      if (i.id == id) {
        return i
      }
    }
    null
  }


  //通过id返回index --返回索引index
  def findIndexById(id: Int): Int = {
    var index = -1
    breakable {
      for (i <- 0 until customers.length) {
        if (customers(i).id == id) {
          index = i
          break()
        }
      }
    }
    index
  }

  //根据id删除客户customer
  def del(id: Int): Boolean = {
    //先进行查找，找到进行，删除，未找到，不删除
    var rstId = findIndexById(id)
    if (rstId == -1) {
      return false
    }
    customers.remove(rstId)
    true
  }

}
