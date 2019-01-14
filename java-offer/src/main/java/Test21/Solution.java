package Test21;



import java.util.Iterator;
import java.util.Stack;


/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））
 */


import java.util.Iterator;
import java.util.Stack;

public class Solution {
    private Stack<Integer> stack = new Stack<>();

    public void push(int node) {
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        int cur = stack.peek();
        Iterator<Integer> iterator = stack.iterator();
        while(iterator.hasNext()){
            int tmp = iterator.next();
            if(cur>= tmp){
                cur = tmp;
            }
        }

        return  cur;
    }
}




//
////==》2
//public class Solution {
//    private Stack<Integer> stack = new Stack<>();
//
//    public void push(int node) {
//        stack.push(node) ;
//    }
//
//    public void pop() {
//        stack.pop();
//    }
//    //top
//    public int top() {
//        return stack.peek();
//    }
//
//    public int min() {
//        int min = stack.peek(); //每次将栈顶元素去除掉，可能题目设计就是如此吧，
//        int tmp = 0;
//        Iterator<Integer> iterator = stack.iterator();
//        while (iterator.hasNext()){
//            tmp = iterator.next();
//            if (min>tmp){
//                min = tmp;
//            }
//        }
//        return min;
//    }
//}


//public class Solution {
//    private Stack<Integer> stack = new Stack<>();
//
//    public void push(int node) {
//        stack.push(node);
//    }
//
//    public void pop() {
//        stack.pop();
//    }
//
//    public int top() {
//        return  stack.peek();
//    }
//
//    public int min() {
//        int min = stack.peek();
//        int tmp = 0;
//        Iterator<Integer> iterator = stack.iterator();
//        while (iterator.hasNext()){
//            tmp = iterator.next();
//            if (min>tmp){
//                min = tmp;
//            }
//        }
//        return min;
//    }
//}
