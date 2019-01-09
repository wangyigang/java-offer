package LeetCode.test09;

import org.junit.Test;

/*
示例 1:

输入: 121
输出: true
示例 2:

输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3:

输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
 */
public class Test09 {
    @Test
    public void test(){
        int x=121;
        System.out.println(isPalindrome(x));
    }
    public boolean isPalindrome(int x) {
        String num = x+"";
        int left = 0;
        int right=num.length()-1;
        while (left<right){
            if(num.charAt(left) != num.charAt(right)){
                return false;
            }else{
                left++;
                right--;
            }
        }
        return true;
    }
}
