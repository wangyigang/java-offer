package month02.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Thread_JUCContainer {
    public static void main(String[] args) {
        Vector<String> vec = new Vector<>();
//        Collections底层使用锁的方式保证线程安全，synchronized 悲观锁的方式
        List<String> strList = Collections.synchronizedList(new ArrayList<>());
        Map<String, Integer> integerMap = Collections.synchronizedMap(new HashMap<String, Integer>());
        Set<String> set = Collections.synchronizedSet(new HashSet<String>());
        //使用JUC --写时复制--使用可重入锁方式ReentrantLock--写数据时，进行复制，并且容器个数+1
        //读数据时，没有加锁，读的时候读取原来的数据
        //list
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        //set
        CopyOnWriteArraySet<String> arraySet = new CopyOnWriteArraySet<>();
        //map
        //利用分段技术和锁技术 分段锁机制
        ConcurrentHashMap map = new ConcurrentHashMap();
    }
}
