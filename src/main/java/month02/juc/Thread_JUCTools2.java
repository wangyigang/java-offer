package month02.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Thread_JUCTools2 {
    public static void main(String[] args) {
        //线程计数器，线程栅栏
        CyclicBarrier cb = new CyclicBarrier(5,()->{
            System.out.println("人员到齐，开始开会...");
        });

        for(int i=0; i<5; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"到达会议室");

                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"人员"+i).start();
        }
    }
}

