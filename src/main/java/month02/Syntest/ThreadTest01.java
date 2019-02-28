package month02.Syntest;

/*  一个对象，两个同步方法,哪一个方法先执行
        如果没有两个线程休眠等场景，两个线程执行顺序不确定
  */

public class ThreadTest01 {
    public static void main(String[] args) throws Exception {

        ShareData01 sd06 = new ShareData01();
        Thread t1 = new Thread(()-> {
            sd06.printA();
        });


        Thread t2 = new Thread(()-> {
            sd06.printB();
        });

        t1.start();
        t2.start();

    }
}

class ShareData01 {
    public synchronized void printA() {

        System.out.println( "aaaa" );
    }

    public synchronized void printB() {
        System.out.println( "bbbb" );
    }

    public void printC() {
        System.out.println( "cccc" );
    }
}
