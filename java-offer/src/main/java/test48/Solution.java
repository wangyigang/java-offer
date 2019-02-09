package test48;

import org.junit.Test;

/*
题目描述
写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class Solution {
    @Test
    public void test(){
        System.out.println(Add(1, 2));
    }

    public int Add(int num1,int num2) {
        int sum=0;
        int carry =0;
        do{
            sum= num1^num2;
            carry = (num1& num2) <<1;

            num1= sum;
            num2= carry;

        }while(num2!= 0);
        return num1;
    }
}