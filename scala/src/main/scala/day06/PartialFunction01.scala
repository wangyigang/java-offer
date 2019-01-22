package day06

//使用偏函数解决前面的问题
object PartialFunction01 {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,"abc")
    //[any,int]--第一个表示参数类型，第二个表示返回类型
    //当使用偏函数时，会遍历集合所有元素，编译器限制性isDefinedAt。如果为true,在执行apply方法
    //map函数不支持偏函数，因为map底层的循环遍历，不支持
    val addOne = new PartialFunction[Any,Int]{

      //需要实现两个方法：第一：isDefinedAt
      //进行判断
      override def isDefinedAt(x: Any): Boolean = {
        if (x.isInstanceOf[Int]) true else false
      }
      //第二：apply方法 --进行添加到新对象中
      override def apply(v1: Any): Int ={
        v1.asInstanceOf[Int]+1 //添加
      }
    }
    val list3 = list.collect(addOne)
    println("list3="+list3)
  }

}


/*
Scala Trait(特征)
Scala Trait(特征) 相当于 Java 的接口，实际上它比接口还功能强大。

与接口不同的是，它还可以定义属性和方法的实现。

一般情况下Scala的类只能够继承单一父类，但是如果是 Trait(特征) 的话就可以继承多个，从结果来看就是实现了多重继承。

Trait(特征) 定义的方式与类类似，但它使用的关键字是 trait，如下所示：
trait--相当于java中的接口，可以实现属性和方法
 */