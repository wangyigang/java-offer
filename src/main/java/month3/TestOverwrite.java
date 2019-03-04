package month3;

public class TestOverwrite {
    public static void main(String[] args) {
        A a = new B();//和对象的
        System.out.println(a.getResult());
    }
}

class A{
    public int i =10;
    public int getResult(){
        return i+10;
    }
}
class B extends  A{
    public int i=20;
    public int getResult(){
        return i+20;
    }
}

