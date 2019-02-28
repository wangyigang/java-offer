package month02.Syntest;

/**
 * 两个对象，两个静态方法，哪一个先执行
 *  两个相同类的静态方法属于同一个，都存储在方法区内，所以属于同一个
 *  执行结果为： 等待4s a 先输出， b 然后再输出
 *
 */
public class ThreadTest07 {
    public static void main(String[] args) throws InterruptedException {
        ShareData07 sd07 = new ShareData07();
        ShareData07 sd77 = new ShareData07();

        Thread t1 = new Thread(() -> {
            sd07.printA();
        });


        Thread t2 = new Thread(() -> {
            sd77.printB();
        });

        t1.start();
        Thread.sleep(100);
        t2.start();

    }
}

class ShareData07 {
    public static synchronized void printA() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aaaa");
    }

    public static synchronized void printB() {
        System.out.println("bbbb");
    }

    public void printC() {
        System.out.println("cccc");
    }
}

