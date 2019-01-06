package month01.generic;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

/*
addFirst:push
removeFirtst():删除栈顶元素
peekFirst():获取栈顶元素，不删除
 */
public class TestLinkedList {
    @Test
    public void test(){
//        LinkedList<Integer> list = new LinkedList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }
        //jdk1.6之后实现了deque接口，
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        Integer integer = list.removeFirst();
        System.out.println("integer:"+integer);
        System.out.println("size:"+list.size());
        Integer peekFirst = list.peekFirst();
        System.out.println("peekfirst:"+peekFirst);
        System.out.println("list.size():"+list.size());
        //linkdedlist 实现队列方式

        Hashtable<String,String> hs=new Hashtable<>();


    }
}
