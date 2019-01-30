package day08

import day08.SeasonEm.SeasonEm


object GenericUst2 {
  def main(args: Array[String]): Unit = {
    //[指定泛型]
    val cla01 = new EnglishClass[SeasonEm,String,String](SeasonEm.spring,"aaa","高级班")
    println(cla01.season + " "+cla01.name+" "+cla01.classType)
  }
}
/*设计一个EnglishClass(英语班级类),在创建Englishclass的一个实例
需要制定[班级开班季节spring,autumn,summer,winter] 班级名称，班级类型
开班季节只能是指定的，班级名称为String，班级类型是(字符串类型“高级班”，“初级班”..)
或是Int类型（1,2,3)
使用泛型来完成本案例
*/
object SeasonEm extends Enumeration{
  type SeasonEm = Value //自定义seasionEm,是value类型，这样才能使用
  val spring,summer,winter,autumn = Value
}

//根据业务需要，设计带有泛型的类
class EnglishClass[A,B,C](val season:SeasonEm,val name:B, val classType:C)
