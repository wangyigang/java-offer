package day08

object ViewBoundsTest01 {
  def main(args: Array[String]): Unit = {
    //支持隐式类型转换--11.f为scala中的Float,不需要手动转换成java.lang.Float
    val comparaComm = new CompareComm[java.lang.Float](11.1f, 10.3f)
    println("comparaComm="+comparaComm.greater)
  }
}
/*
T <% Comparable[T] 表示视图界定
T 是Comparable[T]子类型，同时也支持隐式类型转换
 */
class CompareComm[T<% Comparable[T]](obj1:T, obj2:T){
  def greater = if (obj1.compareTo(obj2)>0) obj1 else obj2
}


