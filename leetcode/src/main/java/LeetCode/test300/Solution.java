package LeetCode.test300;
import org.junit.Test;

/*
给定一个无序的整数数组，找到其中最长上升子序列的长度。
示例:
输入: [10,9,2,5,3,7,101,18]
输出: 4
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为 O(n2) 。
进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class Solution {
    @Test
    public void test() {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

    //进行优化--递归改为非递归方式，递归部分全部从缓存中获取
    public int lengthOfLIS(int[] nums) {
        for (int i = 0; i < 10000; i++) {
            f[i] = 0;
        }
        for (int i = 0; i < nums.length; i++) {
            p[i] = nums[i];
        }
        n = nums.length;
        p[n] = 1000000000;
        ++n;
        for (int idx = 0; idx < n; ++idx) {
            int ans = 0;
            for (int i = 0; i < idx; ++i) {
                if (p[idx] > p[i]) {
                    ans = Math.max(ans, f[i]);
                }
            }
            f[idx] = ans + 1;

        }
        return f[n - 1] - 1;
    }

    public static int n;
    public static int[] f = new int[10000];
    public static int[] p = new int[10000];

    //最长上升子序列--时间复杂度O(n2)
//    public int lengthOfLIS(int[] nums) {
//        for (int i = 0; i < 10000; ++i) {
//            f[i] = 0;
//        }
//        for (int i = 0; i < nums.length; ++i) {
//            p[i] = nums[i];
//        }
//        n = nums.length;
//        p[n] = 1000000000;
//        ++n;
//        return robot(n - 1, p) - 1;
//    }
//
//    public static int robot(int idx, int[] nums) {
//        if (idx < 0) {
//            return 0; // 递归结束条件
//        }
//        if (f[idx] > 0) { //有值
//            return f[idx];
//        }
//        int ans = 0;
//        for (int i = 0; i < idx; ++i) {
//            if (nums[idx] > nums[i]) {
//                ans = Math.max(ans, robot(i, nums));
//            }
//        }
//        f[idx] = ans + 1;
//        return ans + 1;
//    }

    //时间复杂度：O(n2)
//    public int lengthOfLIS(int[] nums) {
//        int len = nums.length;
//        if (len < 2) {
//            return len;
//        }
//        int[] dp = new int[len];
//        // 自己一定是一个子序列
//        Arrays.fill(dp, 1);
//        for (int i = 1; i < len; i++) {
//            for (int j = 0; j < i; j++) {
//                if (nums[j] < nums[i]) {
//                    dp[i] = Math.max(dp[j] + 1, dp[i]);
//                }
//            }
//        }
//        int res = dp[0];
//        for (int i = 0; i < len; i++) {
//            res = Math.max(res, dp[i]);
//        }
//        return res;
//    }

}
