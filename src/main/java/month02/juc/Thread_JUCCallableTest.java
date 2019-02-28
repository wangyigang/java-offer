package month02.juc;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Thread_JUCCallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable call = new MyCallable();
        FutureTask<String> task = new FutureTask<>(call);

        Thread t = new Thread(task);
        t.start();

        String result = task.get();
        System.out.println(result);
        System.out.println("main执行结束...");
    }
}

class MyCallable implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("call...");
        return "thread";
    }
}