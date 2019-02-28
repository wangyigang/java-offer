package month02.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Thread_JUCTools3 {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(3);

        for(int i=1; i<=6; i++){
            new Thread(()->{
                try {
                    //抢占资源
                    s.acquire();
                    System.out.println(Thread.currentThread().getName()+">>>进入");
                    Thread.holdsLock(new Random().nextInt(1000));
                    System.out.println(Thread.currentThread().getName()+"<<<退出");

                    //释放资源
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"car"+i).start();
        }
    }
}

