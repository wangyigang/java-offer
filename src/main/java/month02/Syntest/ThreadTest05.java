package month02.Syntest;

/**
 *  一个对象，两个静态同步方法，a,b ,哪一个方法先执行
 *  执行结果：先执行a,在执行B，此时的锁是类锁，static对应的是类锁，a
 *  先获取锁的资源，休眠后，没有释放类锁，b 只能等待，所以a 先输出后，b在输出
 */
public class ThreadTest05 {
    public static void main(String[] args) throws InterruptedException {
        ShareData05 sd07 = new ShareData05();

        Thread t1 = new Thread(()-> {
            sd07.printA();
        });


        Thread t2 = new Thread(()-> {
            sd07.printB();
        });

        t1.start();
        Thread.sleep(100);
        t2.start();

    }
}

class ShareData05{
    public static synchronized void printA() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( "aaaa" );
    }

    public static  synchronized void printB() {
        System.out.println( "bbbb" );
    }

    public void printC() {
        System.out.println( "cccc" );
    }
}

