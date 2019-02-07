package month02.day01;

public class ThreadTest2 {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
    }
}

class MyRunnable implements Runnable {
    //重写run()方法
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "线程:" + i);
        }
    }
}