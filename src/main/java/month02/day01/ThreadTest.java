package month02.day01;

public class ThreadTest {
    public static void main(String[] args) {
        Mythread mythread = new Mythread();
        mythread.start();
    }
}

class Mythread extends Thread {
    //重写run方法
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(super.getName() + "线程:" + i);
        }
    }
}