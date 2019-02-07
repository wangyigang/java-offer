package month01;

public class Testinterface {
    public static void main(String[] args) {
//        new SubA().methodA();
        A.testA(); //静态方法通过接口直接调用
    }
}
interface A{
//    default void methodA(){
//        System.out.println("A的默认实现...");
//    }
    public static void testA(){
        System.out.println("A的静态方法...");
    }
}

class SubA implements A{
    //继承接口A后，即使什么也不写，也有一个默认方法methodA
}

