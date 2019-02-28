package month02.juc;

import java.util.concurrent.atomic.AtomicInteger;

/*
 synchronized 锁属于重量级锁，比较笨重不方便，锁嵌套不能释放
 //		// synchronized
//		// 重量级锁
//
//		synchronized("123") { // 加锁1
//			synchronized("456") { // 加锁2
//				synchronized("789") { // 加锁3
//
//				} // 释放锁3
//			}//释放锁2
//
//		} // 释放锁1
 */
public class AtomicIntegerTest {
    public static void main(String[] args) throws InterruptedException {
        ShareData sd = new ShareData();
        Thread t1 = new Thread(()->{
            for (int i=0; i<10 ; i++) {
                sd.produce();
            }
        });
        Thread t2 = new Thread(()->{
            for ( int i = 0; i < 10; i++ ) {
                sd.consume();
                //System.out.println( "count consume = " + sd04.count );
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("final = "+ sd.count);
    }
}
class ShareData{
    public AtomicInteger count = new AtomicInteger();

    public void produce(){
        //增加
        count.getAndIncrement();
    }
    public void consume(){
        //减少
        count.getAndDecrement();
        //源码中使用了CAS乐观锁的方式， 底层实现的方式是cas技术，do while() 判断， do 中先获取原始数据， while()中comandswap()方法进行比较，
//        AtomicReference<v> 是一个抽象，可以封装任意类型的数据
    }
}
