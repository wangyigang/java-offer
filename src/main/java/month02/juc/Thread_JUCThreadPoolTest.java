package month02.juc;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Thread_JUCThreadPoolTest {
    public static void main(String[] args) {
        //创造单一线程
        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 创造单一线程
        //newFixedThreadPool创建指定数量线程池
        threadPool = Executors.newFixedThreadPool(10);
        //创建自定义线程池
        threadPool = Executors.newCachedThreadPool();

        //功能
        for (int i = 1; i <= 20; i++) {
            threadPool.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName()+"执行");
                    return "call";
                }
            });

        }

        //关闭线程池
        threadPool.shutdown();
    }
}
