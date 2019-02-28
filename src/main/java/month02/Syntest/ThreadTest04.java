package month02.Syntest;

/**
 *  两个对象，两个同步方法(a, b ) 哪一个方法先执行
 *  结果为：先打印B，等待4s后在打印A，两个对象之间，没有关系，所以不会互相阻
 *  对象，B先输出，a 在4s后打印a
 *
 */
public class ThreadTest04 {
    public static void main(String[] args) throws InterruptedException {

        ShareData04 sd06 = new ShareData04();
        ShareData04 sd66 = new ShareData04();

        Thread t1 = new Thread(()-> {
            sd06.printA();
        });



        Thread t2 = new Thread(()-> {
            sd66.printB();
        });

        t1.start();
        Thread.sleep(100);
        t2.start();
    }
}

class ShareData04 {
    public synchronized void printA() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( "aaaa" );
    }

    public synchronized void printB() {
        System.out.println( "bbbb" );
    }

    public void printC() {
        System.out.println( "cccc" );
    }
}
