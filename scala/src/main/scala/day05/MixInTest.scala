package day05

object MixInDemo {
  def main(args: Array[String]): Unit = {

    //创建OracleDB 实例，同时动态混入Operate3特质
    //就可以使用特质的方法,理解解耦接入.
    val oracleDB = new OracleDB with Operate3

    oracleDB.insert(100) //

    //
    //    new MySQL3 with Operate3 {
    ////      override def insert2(): Unit = {
    ////
    ////      }
    //
    //      override def sayHi: Unit = {
    //
    //      }
    //    }
    //
    //    //如果我们要去实例化一个abstract 类，也可以，但是需要时候用匿名子类来构建
    //    //语句
    //    val mySQL = new MySQL3 {
    //      override def sayHi: Unit = {
    //
    //      }
    //    }
    //
    //  }
  }
}


//特质
trait Operate3 {
  def insert(id: Int): Unit = {
    println("插入数据 = " + id)
  }
 // def insert2()
}
//普通类
class OracleDB {
}

//抽象类
abstract class MySQL3 {
  //def sayHi
}