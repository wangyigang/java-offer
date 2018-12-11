package day1210.javase;

import org.junit.Test;

public class test {
    /**
      tostring:
     1.默认情况下输出:当前类getClass.getname@hashcode()的十六位编码
     getClass.getname@对象hashcode十六进制编码
     2.进行string与其他数据类型连接操作时，自动调用toString()方法
     3.
     */
    @Test
    public void test1(){
        Student student = new Student();
        System/**/.out.println(student.toString());  //Student@5d6f64b1
        System.out.println(student.getClass().getName());
    }
    /*
    finalize():
    1.finalize（）一般由垃圾回收器调用，程序员一般很少手动调用
    2.一个对象的finalize（）方法只能调用一次
    3.一般甚好重写，只有连接某种资源时，才会进行
     */


    /*
    static:
    希望某些数据无论产生多少对象，只生成一份
    类变量：
    静态变量：当某个属性是所有对象共享的，那么这样的属性应声明为静态的
    特点：随着类的初始化而初始化，优先于实例对象创建
    被所有类共享
    所访问时，可以不创建对象，直接使用 类名.xx 调用
    类变量的值存储在方法区
    类的get/set、方法也是静态的

    类方法：

     */
    @Test
    public void test2(){
        //如果是private 的静态变量，不可访问
        System.out.println(Student.sint);
    }
    /*
    代码块：
    静态代码块：
    1可以为类变量(静态属性)初始化
    2.随着类的初始化而初始化，只执行一次
    3.
     */
    @Test
    public void test3(){
        
    }
}

class Student{
    public static  int sint =1;
    private String name;
    private String grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}