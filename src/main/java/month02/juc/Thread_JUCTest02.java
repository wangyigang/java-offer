package month02.juc;


import java.util.concurrent.locks.ReentrantLock;

/*
    注意点: 条件变量的signalall 需要卸载解锁前面， 锁后面，会爆出异常
 */
public class Thread_JUCTest02 {
    public static void main(String[] args) throws InterruptedException {
        ShareData02 sd10 = new ShareData02();

        for ( int i = 1; i <= 2; i++ ) {
            new Thread(()-> {
                sd10.print();
            }, "线程"+i).start();
        }
    }

}

class ShareData02 {
    //公平锁 --底层好像通过队列方式获取线程, 通过aqs 链表实现的同步队列方式获取线程，然后通过cas乐观锁方式
    ReentrantLock lock = new ReentrantLock(true);

    public void print() {
        while ( true ) {
            lock.lock();
            System.out.println( Thread.currentThread().getName() + "-打印消息" );
            lock.unlock();
        }
    }
}
