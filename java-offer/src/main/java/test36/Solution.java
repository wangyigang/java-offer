package test36;


import org.junit.Test;

/**
 * 题目描述
 * 统计一个数字在排序数组中出现的次数。
 */
public class Solution {
    @Test
    public void  test(){
        int[] arr= new int[]{1,2,2,2,3,4,5,6,7};
        System.out.println(GetNumberOfK(arr, 2));
    }
    //获取一个数字在排序数组中出现的次数
    public int GetNumberOfK(int [] array , int k) {
        int count =0;
        for (int num : array) {
            if(num == k){
                count++;
            }
        }
        return count;
    }
}