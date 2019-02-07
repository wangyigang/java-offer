package month01;

interface Filial{ //孝顺的
    default void help(){
        System.out.println("老妈.我来救你了...");
    }
}
interface Spoony{
    default void help(){
        System.out.println("老婆.我来救你了...");
    }
}

class Main implements Filial,Spoony{
    //当实现了两个接口后，编译器直接提示不能编译通过，两个默认方法冲突，

    @Override
    public void help() {
        Filial.super.help(); //保留Filial的默认方法
        Spoony.super.help(); //保留Spoony的默认方法
        System.out.println("快跑..."); //或者重新实现
    }
}
class Father{

    public void help(){
        System.out.println("儿子，救我老婆");
    }
}
class Man extends Father implements Filial,Spoony{
    @Override
    public void help() {
        Filial.super.help(); //保留Filial的默认方法
        Spoony.super.help(); //保留Spoony的默认方法
        System.out.println("快跑..."); //或者重新实现
    }
}

public class Testinterface1 {

    public static void main(String[] args) {
        Man m = new Man();
        m.help();  //默认调用的是父类中的同名方法
    }

}