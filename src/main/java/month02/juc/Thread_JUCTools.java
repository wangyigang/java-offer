package month02.juc;

import java.util.concurrent.CountDownLatch;

public class Thread_JUCTools {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);

        for(int i=1; i<=5; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"打扫完成");
                latch.countDown();
            }, "学生"+i).start();
        }
        latch.await();
        System.out.println("老师锁门");
    }
}

