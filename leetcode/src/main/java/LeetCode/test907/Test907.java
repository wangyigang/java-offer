package LeetCode.test907;

import org.junit.Test;

import java.util.Stack;

/*
给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。
输入：[3,1,2,4]
子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
输出：17
 */
public class Test907 {
    private static int mod = 1000000007;

    @Test
    public void test() {
        int[] arr = new int[]{3, 1, 2, 4};
        System.out.println(sumSubarrayMins(arr));
    }

    public int sumSubarrayMins(int[] A) {
        int n = A.length;
        int res = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.empty() && A[st.peek()] > A[i])
                st.pop();
            int left = st.empty() ? -1 : st.peek();
            st.push(i);
            int j = i + 1;
            while (j < n && A[j] >= A[i])
                j++;

            res = (res + ((i - left) * (j - i) % mod) * A[i]);
            res = (res % mod);
        }
        return res;
    }


//    //超出时间限制
//    public int sumSubarrayMins(int[] A) {
//        int[] minArr = A.clone();
//        int res = 0;
//        //遍历数组a
//        for (int i = 0; i < A.length; i++)
//            //遍历
//            for (int j = 0; j + i < A.length; j++) {
//                minArr[j] = Math.min(minArr[j], A[i + j]);//求出下标j中和下标j在i字后位置处哪个最小,找出最小值
//                res += minArr[j];
//                res %= 1000000007;
//            }
//        return res;
//    }

}


/*
    class Solution {
        public int sumSubarrayMins(int[] A) {
            final int kMod = 1000000007;
            final int n = A.length;
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                int left = 0;
                for (int j = i - 1; j >= 0 && A[j] > A[i]; --j, ++left);
                int right = 0;
                for (int j = i + 1; j < n && A[j] >= A[i]; ++j, ++right);
                ans = (int)((ans + (long)A[i] * (left + 1) * (right + 1)) % kMod);
            }
            return ans;
        }
    }
 */