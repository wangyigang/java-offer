package DesignMode.decorate;

//主函数--客户端
public class TestDecorate {
    public static void main(String[] args) {
        Component component = new ConcreteDecorate1(new ConcreteDecorate2(new ConcreteComponent()));
        component.doSomething();
    }
}

//抽象类
interface Component{
    public void doSomething();
}

//具体的构建角色：
class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}

//3)    装饰角色：
class Decorate implements Component {
    private Component component;
    //构造器
    public Decorate(Component component) {
        this.component = component;
    }
    @Override
    public void doSomething() {
        component.doSomething();
    }
}

//4)    具体装饰角色1：
class ConcreteDecorate1 extends Decorate {
    public ConcreteDecorate1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherDosomething(); //之后增加新的功能
    }

    private void doAnotherDosomething() {
        System.out.println("功能B");
    }
}

//5)    具体装饰角色2：
class ConcreteDecorate2 extends Decorate {
    public ConcreteDecorate2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherDosomething();
    }
    private void doAnotherDosomething() {
        System.out.println("功能C");
    }
}

