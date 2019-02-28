package month02.thread;

public class TestProducerConsumer {
    public static void main(String[] args) {
        ShareData sd = new ShareData();
        Thread t1 = new Thread(() -> {
            for (int i=0; i<10; i++) {
                sd.product();
            }
        });

        Thread t2 = new Thread(()->{
            for (int i=0; i<10; i++) {
                sd.consumer();
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i=0; i<10; i++) {
                sd.product();
            }
        });

        Thread t4 = new Thread(()->{
            for (int i=0; i<10; i++) {
                sd.consumer();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();


        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main count="+sd.count);
//        System.out.println("main....");
    }
}

class ShareData{
    public int count =0;
    //生产者
    public synchronized  void product(){
        while(count ==1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //生产
        count++;
        System.out.println("product count="+count);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notifyAll();
    }
    //消费者
    public synchronized  void consumer(){
        //当结果没有时，就进行等待
        while(count ==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //消费
        count--;
        System.out.println("consumer count="+count);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }
}

