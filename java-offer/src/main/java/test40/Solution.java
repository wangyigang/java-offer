package test40;

//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果


import org.junit.Test;

/**
 * 题目描述
 * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。
 * 请写程序找出这两个只出现一次的数字。
 */
public class Solution {
    @Test
    public void test(){
        int[] arr = new int[]{1,1,2,3,4,4};
        int[] ret1= new int[1];
        int[] ret2= new int[1];
        FindNumsAppearOnce(arr, ret1, ret2);
        System.out.println(ret1[0]);
        System.out.println(ret2[0]);
    }
    //异或思想：题中除了两个数之外，其余都是偶数次
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == null || array.length <2) {
            return;
        }
        int res = 0;
        for (int num :array) {
            res ^= num;
        }

        int index = 0;
        while((res & 1)==0) {
            index++;
            res = res >> 1;
        }
        for (int num :array) {
            if ((num>>index & 1) != 0){
                num1[0] ^= num;
            } else {
                num2[0] ^= num;
            }
        }
    }
}