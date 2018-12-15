package LeetCode.Test55;

import org.junit.Test;

/*
给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个位置。

示例 1:
输入: [2,3,1,1,4]
输出: true
解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 */
//public class Test55 {
//    @Test
//    public void test(){
//        int[] arr = new int[]{2,3,1,1,4};
//        boolean b = canJump(arr);
//        System.out.println(b);
//    }
//    /*
//    贪心算法：
//    只注意当下最大的
//     */
//    public boolean canJump(int[] nums) {
//        int rch = 0;
//        for (int i = 0; i <= rch; i++) {
//            rch = Math.max(rch, nums[i] + i);
//            if (rch >= nums.length - 1)
//                return true;
//        }
//        return false;
//    }
//}


public class  Test55{
    @Test
    public void test(){
        int[] arr = new int[]{3,2,1,0,4};
//        int[] arr = new int[]{2,3,1,1,4};
        System.out.println(canJump(arr));
    }
    //贪心算法思想:贪心选择是指所求问题的整体最优解可以通过一系列局部最优的选择，即贪心选择来达到。
    public boolean canJump(int[] nums) {
        int rch =0;
        for(int i=0; i<=rch; ++i){
            //注意更新rch
            rch = Math.max(rch, nums[i]+i); // nums[i]+i代表当前走的步数
            // 当前已走的步数+当前可走的步数，能否到最重点，因为贪心算法，只关注当前，不关注其他
            //rch 0, 3 =>3
            //3, 3=>3
            //3,3 =>3
            if(rch>= nums.length-1){ //nums.length-1 =>4
                return true;
            }
        }
        return false;
    }
}