package month02.Syntest;

/**
 * 一个对象，一个静态同步方法，一个成员同步方法
 *  执行结果为： 先输出b ,在输出a ,因为类锁和对象锁两个锁无关，所以
 *  b的执行与a的类锁无关，不会互相影响， b先输出， a 休眠4s后 继续执行输出
 */
public class ThreadTest06 {
    public static void main(String[] args) throws InterruptedException {
        ShareData06 sd06 = new ShareData06();

        Thread t1 = new Thread(() -> {
            sd06.printA();
        });


        Thread t2 = new Thread(() -> {
            sd06.printB();
        });

        t1.start();
        Thread.sleep(100);
        t2.start();

    }
}

class ShareData06 {
    public static synchronized void printA() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aaaa");
    }

    public synchronized void printB() {
        System.out.println("bbbb");
    }

    public void printC() {
        System.out.println("cccc");
    }
}

