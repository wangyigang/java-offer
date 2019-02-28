package month02.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Thread_JUCReadWriteTest {
    public static void main(String[] args) {
        ReadWriteData data = new ReadWriteData();

        new Thread(()-> {
            data.write("Hello Thread!!!");
        }).start();

        for ( int i = 1; i <= 20; i++ ) {
            new Thread(()-> {
                data.read();
            }, "学生"+i).start();
        }
    }
}
class ReadWriteData{
    public  String content = "";

    private ReentrantReadWriteLock rwlck = new ReentrantReadWriteLock();

    public  void write(String s){
        rwlck.writeLock().lock(); //写锁锁定

        content = s;
        System.out.println(content);

        rwlck.writeLock().unlock(); //解锁
    }
    public void read(){
        rwlck.readLock().lock();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" 读取:"+content);
        rwlck.readLock().unlock();
    }
}
