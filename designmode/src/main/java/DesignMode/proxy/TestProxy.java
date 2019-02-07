package DesignMode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {
    public static void main(String[] args) {
        //获取代理者的Class对象
        Class c = MySQLVersion.class;
        //获取它的类加载器对象
        ClassLoader loader = c.getClassLoader();
        //创造InvocationHandler对象，
        MyInvocationHandler my = new MyInvocationHandler(new MySQLVersion(), true);
        //创建Proxy的instance
        //第一个参数类加载器对象，给他穿代理者的类加载器对象
        //第二个参数：被代理者的实现接口们
        //第三个参数：代理者要替被代理者完成的代理工作的处理器对象
        Object proxyInstance = Proxy.newProxyInstance(loader, c.getInterfaces(), my);

        //转型
        IGoods iGoods = (IGoods) proxyInstance;
        iGoods.add();
        iGoods.delete();
        iGoods.update();
        iGoods.delete();
    }
}

//实现InvocationHandler方法
class MyInvocationHandler implements InvocationHandler{
    private Object target;
    private boolean flag;

    public MyInvocationHandler(Object target, boolean flag) {
        super();
        this.target = target;
        this.flag = flag;
    }

    /*
        重写invoke方法:
        第一个参数：proxy:代理对象
        第二个参数：被代理者要执行的方法
        第三个参数：要穿个代理者执行的方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if(flag){
            System.out.println("开始记录日志...");
            result = method.invoke(target, args);
            System.out.println("结束记录日志...");
        }else{
            result = method.invoke(target, args);
        }
        return result;
    }
}




//主题接口
interface IGoods{
    void add();
    void delete();
    void update();
    void list();
}
class ORacleVersion implements IGoods{

    @Override
    public void add() {
        System.out.println("oracle版添加商品...");
    }

    @Override
    public void delete() {
        System.out.println("oracle版删除商品...");
    }

    @Override
    public void update() {
        System.out.println("oracle版更新商品...");
    }

    @Override
    public void list() {
        System.out.println("oracle版查询商品...");
    }
}

class MySQLVersion implements IGoods{

    @Override
    public void add() {
        System.out.println("mysql版添加...");
    }

    @Override
    public void delete() {
        System.out.println("mysql版删除...");
    }

    @Override
    public void update() {
        System.out.println("mysql版更新...");
    }

    @Override
    public void list() {
        System.out.println("mysql版查询商品");
    }
}