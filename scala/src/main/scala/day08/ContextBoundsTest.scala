package day08

object ContextBoundsTest {
  def main(args: Array[String]): Unit = {

  }

}


//方式1
//说明 1.[T:Ordering] 泛型
//implicit隐式参数
class CompareComm4[T: Ordering](obj1: T, obj2: T)(implicit comparetor: Ordering[T]) {
  def geatter = if (comparetor.compare(obj1, obj2) > 0) obj1 else obj2
}
class CompareComm5[T:Ordering](o1:T,o2:T){
  def greatter={

  }
}

class Person(val name:String,val age :Int){
  //重写tostring 方法
  override def toString: String = this.name+"\t"+this.age

}

