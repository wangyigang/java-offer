package month02.Syntest;

/*  一个对象，两个同步方法,线程a中休眠4s
    线程1先获取锁资源，休眠4s，休眠过程中不释放所资源，
    线程2获得cpu资源后，无法获取锁，所以只能等待a打印输出后，再输出b

 */

public class ThreadTest02 {
    public static void main(String[] args) throws Exception {
        ShareData02 sd02 = new ShareData02();

        Thread t1 = new Thread(()-> {
            sd02.printA();
        });

        Thread t2 = new Thread(()-> {
            sd02.printB();
        });

        t1.start();
        Thread.sleep(100);
        t2.start();

    }


}
class ShareData02{
    public synchronized  void printA(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aaaaa");
    }

    public synchronized  void printB(){
        System.out.println("bbbbb");
    }

}