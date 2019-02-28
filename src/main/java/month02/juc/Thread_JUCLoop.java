package month02.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_JUCLoop {
    public static void main(String[] args) {
        ShareData12 sd12 = new ShareData12();

        Thread t1 = new Thread(()-> {
            for (int i = 1; i <= 3; i++) {
                sd12.print5();
            }

        });

        Thread t2 = new Thread(()-> {
            for (int i = 1; i <= 3; i++) {
                sd12.print10();
            }

        });

        Thread t3 = new Thread(()-> {
            for (int i = 1; i <= 3; i++) {
                sd12.print15();
            }

        });

        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * 线程接力的循环打印
 */
class ShareData12{
    private ReentrantLock lck = new ReentrantLock();
    private Condition cond1 =  lck.newCondition() ;
    private Condition cond2 =  lck.newCondition() ;
    private Condition cond3 =  lck.newCondition() ;

    private int printNum = 5;

    public void print5(){
        lck.lock();

        while(printNum != 5){
            try {
                cond1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //业务逻辑
        for(int i=1; i<=5;i++){
            System.out.println("i="+i);
        }
        //先改变
        printNum = 10;
        //在通知
        cond2.signal();
        //再释放锁
        lck.unlock();
    }

    public void print10(){
        lck.lock();
        while(printNum !=10){
            try {
                cond2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //业务逻辑
        for(int i=6; i<=10; i++){
            System.out.println("i="+i);
        }
        //改变值
        printNum =15;
        cond3.signal();
        //释放锁
        lck.unlock();
    }
    public void print15() {

        lck.lock();

        while ( printNum != 15 ) {
            try {
                cond3.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for ( int i = 11; i <= 15; i++ ) {
            System.out.println( "i = " + i );
        }

        printNum = 5;

        cond1.signal();

        lck.unlock();
    }
}
