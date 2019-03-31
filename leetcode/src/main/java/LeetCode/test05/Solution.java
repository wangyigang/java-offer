package LeetCode.test05;


import org.junit.Test;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 */

public class Solution {

    @Test
    public void test(){
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }



    /**
     * 查找最长的回文子串
     * @param s
     * @return
     */
    /*
        总结：1.
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() <1){
            return "";
        }
        int start =0; //记录最终起始位置索引index
        int end =0; //记录最终结束索引位置index
        for(int i =0; i<s.length(); i++){
            //按照两种方式判断最长回文串 --基数长度进行判断
            int len1 = expandAroundCenter(s, i, i);
            //偶数长度方式判断
            int len2  = expandAroundCenter(s, i, i+1);
            //获取最长字符串
            int maxLen = Math.max(len1, len2);
            //判断长度
            if(maxLen > end -start){
                //aba  1-1  --这里也需要注意两种情况，分别是偶数和基数索引的场景开始
                start = i - (maxLen - 1) / 2; //赋值起始索引
                end = i + maxLen / 2; //结束索引
            }

        }
        return s.substring(start, end+1); //左闭右开，所以进行+1
    }

    private int expandAroundCenter(String s, int left, int right) {
        int l =left;
        int r = right;
        while(l>=0 && r <s.length() && s.charAt(l) == s.charAt(r)){  //左右两侧相等
            //如果相等，继续向两侧进行扩展
            l--;
            r++;
        }
        return r-l-1; //
    }

    /**
     *  首先确认中间位置：分两种情况： 字符串长度为奇数和偶数场景
     *          1.奇数场景：
     *          2.偶数场景:
     *
     */
//    public String longestPalindrome(String s) {
//        if (s == null || s.length() < 1)
//            return "";
//        int start = 0, end = 0;  //记录起始位置和结束位置
//        for (int i = 0; i < s.length(); i++) {
//            //按照奇数方式扩展
//            int len1 = expandAroundCenter(s, i, i);
//            //按照偶数方式进行扩展
//            int len2 = expandAroundCenter(s, i, i + 1);
//            int len = Math.max(len1, len2);
//            if (len > end - start) {//如果长度大于现有的长度，进行替换
//                start = i - (len - 1) / 2; //赋值起始索引
//                end = i + len / 2; //结束索引
//            }
//        }
//        return s.substring(start, end + 1);  //截取字符串，并返回
//    }
//
//    private int expandAroundCenter(String s, int left, int right) {
//        int L = left, R = right;
//        //判断左右两侧数据是否相同
//        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
//            //1.判断没有越界2. 左边的值和右边的值相同
//            L--;
//            R++;
//        }
//        return R - L - 1;
//    }
}