package month02.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
    注意点: 条件变量的signalall 需要卸载解锁前面， 锁后面，会爆出异常
 */
public class Thread_JUCTest01 {
    public static void main(String[] args) throws InterruptedException {
        ShareData01 sd04 = new ShareData01();

        // 生产者
        Thread t1 = new Thread(()->{
            for ( int i = 0; i < 10; i++ ) {
                sd04.produce();

                //System.out.println( "count produce = " + sd04.count );
            }
        });

        // 消费者
        Thread t2 = new Thread(()->{
            for ( int i = 0; i < 10; i++ ) {
                sd04.consume();
                //System.out.println( "count consume = " + sd04.count );
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println( "final = " + sd04.count );
        System.out.println( "main方法执行完毕" );
    }
}

class ShareData01 {
    //可重入锁
    ReentrantLock lck = new ReentrantLock();
    //条件变量
    Condition cond = lck.newCondition();


    public int count = 0;

    //生产数据
    public void produce() {
        lck.lock();
        while(count==1){
            try {
                //await() 进行等待
                cond.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;

        cond.signalAll();//通知所有人
        lck.unlock();
        //通知
    }

    public void consume() {
        lck.lock();

        while(count==0){
            try {
                cond.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count--;

        cond.signalAll();
        //解锁
        lck.unlock();
    }

}
