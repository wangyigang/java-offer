package DesignMode.teplate;

public class TestTemplate {
    public static void main(String[] args) {
        MyCallTime myCallTime = new MyCallTime();
        myCallTime.getTime();//调用getTime()方法--入口函数
    }

}
class MyCallTime extends CalTime{
    //继承后实现method方法
    @Override
    protected void method() {
        long sum =0;
        for(int i=0; i<= 1000000; i++){
            sum+=i;
        }
        System.out.println("结果:"+sum);
    }
}

abstract class CalTime{
    public  final void getTime(){
        //先获取开始时间
        long start = System.currentTimeMillis();
        //调用method方法
        method();
        //获取结束时间
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "毫秒");
    }
    protected  abstract void method();
}
