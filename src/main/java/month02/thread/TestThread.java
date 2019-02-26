package month02.thread;

public class TestThread {
    public static void main(String[] args) {
        Mydata m = new Mydata();
        Thread t1 = new Thread(() -> {
            for (int i=0; i<100; i++){
                m.name = "wangyigang";
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(m.name);
            }
        });
        Thread t2 = new Thread(()->{
            m.name = "dilireba";
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(m.name);

        });
        t1.start();
        t2.start();
        System.out.println("main...");
    }
}

class Mydata {
    public String name = "";
}