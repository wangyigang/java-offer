package month02.Syntest;

/**
 *恋歌对象，一个静态同步方法a ,一个成员同步方法b, 哪一个先执行
 * 执行结果为： C 先执行，执行完后， a 等待4s后继续输出
 */
public class ThreadTest08 {
    public static void main(String[] args) throws InterruptedException {
        ShareData08 sd07 = new ShareData08();

        Thread t1 = new Thread(() -> {
            sd07.printA();
        });


        Thread t2 = new Thread(() -> {
            sd07.printC();
        });

        t1.start();
        Thread.sleep(100);
        t2.start();

    }
}

class ShareData08 {
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

