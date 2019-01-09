package LeetCode.test01;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
 */
public class Test01 {
    @Test
    public void test(){
        int[] arr = {2, 7, 11, 15};
        int[] sum = twoSum(arr, 9);
        for (int i : sum) {
            System.out.println(i);
        }
    }
    public int[] twoSum(int[] nums, int target) {
        //定义一个map //key存储数值，value存储下标
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; ++i){
            int complement = target-nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i],i);
        }
        throw new RuntimeException("no two result");
    }

    //一边找，一边插入数据--厉害
//    public int[] twoSum(int[] nums, int target) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            int complement = target - nums[i];
//            if (map.containsKey(complement)) {
//                return new int[] { map.get(complement), i };
//            }
//            map.put(nums[i], i);
//        }
//        throw new IllegalArgumentException("No two sum solution");
//    }

//    public int[] twoSum(int[] nums, int target) {
//        int[] arr = new int[2];
//        for(int i=0; i<nums.length; ++i){
//            for(int j=i+1; j<nums.length;++j){
//                if(nums[i]+ nums[j] == target){
//                    arr[0] = i;
//                    arr[1] = j;
//                    break;
//                }
//            }
//        }
//        return arr;
//    }


//    /*
//    此方法有缺陷：要求此方法一定是输入数据有序，所以针对于无序的数据，不可用
//
//     */
//    public int[] twoSum(int[] nums, int target) {
//        int[] arr = new int[2];
//        //左右两个指针指向头和尾
//        int left = 0;
//        int right = nums.length-1;
//        while(left<= right){
//            if(nums[left]+ nums[right] == target){
//                arr[0]=left;
//                arr[1]=right;
//                break;
//            }else if(nums[left]+ nums[right] <target){
//                left++;
//            }else{
//                right--;
//            }
//        }
//        return arr;
//    }
}
