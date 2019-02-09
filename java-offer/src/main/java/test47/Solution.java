package test47;

import org.junit.Test;

/*
题目描述
求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case
等关键字及条件判断语句（A?B:C）。
 */
public class Solution {
    @Test
    public void test() {
        System.out.println(Sum_Solution(100));
    }

    //递归方式
    public int Sum_Solution(int n) {
        int sum = n;
        //利用短路与特点--当第一个条件不满足时，不进行右边的操作，所以递归结束
        boolean ans = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }
}