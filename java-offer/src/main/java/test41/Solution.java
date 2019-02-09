package test41;



/*
题目描述
输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
如果有多对数字的和等于S，输出两个数的乘积最小的。
输出描述:
对应每个测试案例，输出两个数，小的先输出。
 */
import org.junit.Test;

import java.util.ArrayList;
public class Solution {
    @Test
    public void test(){
        int[] arr = new int[]{1,2,4,7,11,15};
        for (Integer integer : FindNumbersWithSum(arr, 15)) {
            System.out.println(integer);
        }
    }

    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        //防御性编程
        if(array== null||array.length==0){
            return list;
        }
        int left =0;
        int right= array.length-1;

        while (left <= right) {
            int tmp = array[left] + array[right];
            if(tmp == sum){
                list.add(array[left]);
                list.add(array[right]);
                break;
            }else if(tmp > sum){
                right--;
            }else{
                left++;
            }
        }
        return list;
    }
}