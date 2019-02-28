package month02.Syntest;

/**
 *  一个对象，一个同步方法a, 一个普通成员方法c,哪一个方法先执行
 *  普通方法和锁无关，所以普通方法的运行和锁无关，不会影响普通方法的uyunxing，
 *  所以c会先输出，a 在休眠4s后，输出a
 *
 */
public class ThreadTest03 {
    public static void main(String[] args) throws InterruptedException {

        ShareData03 sd06 = new ShareData03();

        Thread t1 = new Thread(()-> {
            sd06.printA();
        });


        Thread t2 = new Thread(()-> {
            sd06.printC();
        });

        t1.start();
        Thread.sleep(100);
        t2.start();
    }
}

class ShareData03 {
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
