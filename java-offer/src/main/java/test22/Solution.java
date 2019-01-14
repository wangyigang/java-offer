package test22;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 题目描述
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */


import java.util.Stack;

public class Solution {
    //==》3：
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        //防御性编程--判断异常情况
        if(pushA.length ==0 || popA.length ==0){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int j=0;
        for(int i=0; i<pushA.length; ++i){
            stack.push(pushA[i]);
            while(j<pushA.length && stack.peek() == popA[j]){
                //1.不能越界 2.模拟栈出方式，如果相同，
                stack.pop();
                j++;
            }

        }
        return stack.empty()? true:false;
    }


    //判断入栈和出栈顺序是否正确--模拟出啊出栈顺序
    //==>1
//    public boolean IsPopOrder(int[] pushA, int[] popA) {
//        if(popA.length ==0 || popA.length ==0){
//            return false;
//        }
//
//        Stack<Integer> stack = new Stack<>();
//        int j =0;
//        for(int i=0; i<pushA.length; ++i){
//            stack.push(pushA[i]);
//            while(j<pushA.length && stack.peek() == popA[j]){ //防止越界
//                stack.pop();
//                j++;
//            }
//
//        }
//        return stack.empty() ? true: false;
//    }




//    public boolean IsPopOrder(int[] pushA, int[] popA) {
//        if (pushA.length == 0 || popA.length == 0) {
//            return false;
//        }
//        //创建一个栈
//        Stack<Integer> stack = new Stack<>();
//        int j = 0;
//        for (int i = 0; i < popA.length; i++) { //for循环
//            stack.push(pushA[i]);//将pushA中数据逐个放入到stack栈中
//            while (j < popA.length && stack.peek() == popA[j]) { //while循环--循环后，不能大于A的长度
//                stack.pop(); //如果stack的栈顶和popA出栈顺序A相同，就pop
//                j++; //记录个数
//            }
//        }
//        return stack.empty() ? true : false; //最后判断只要为空就说明出栈顺序正确
//    }

    //进行测试
    @Test
    public void test() {
        int[] pushA = {1, 2, 3, 4, 5};
        int[] popA = {4, 5, 3, 2, 1};
        System.out.println(IsPopOrder(pushA, popA));
    }
}