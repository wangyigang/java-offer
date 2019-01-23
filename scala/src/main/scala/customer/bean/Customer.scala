package customer.bean

class Customer {
  var id: Int = _ //id
  var age: Int = _ //年龄
  var name: String = _
  //姓名
  var gender: Char = _
  //性别
  var phone: String = _
  //电话
  var email: String = _ //邮箱

  //id为进行初始化,希望设置为自增长类型
  //构造器定义 def this() //辅构造器
  def this (age: Int, name: String, gender: Char, phone: String, email: String) {
    this //先调用主构造器
    this.age = age
    this.name = name
    this.gender = gender
    this.phone = phone
    this.email = email
  }

  def this (id:Int, age: Int, name: String, gender: Char, phone: String, email: String) {
    this //先调用主构造器
    this.id= id
    this.age = age
    this.name = name
    this.gender = gender
    this.phone = phone
    this.email = email
  }

  //重写tostring 方法
  override def toString: String = {
    return this.id + "\t\t" + this.name + "\t\t" + this.gender + "\t\t" + this.age + "\t\t" + this.phone + "\t\t" + this.email
  }
}
