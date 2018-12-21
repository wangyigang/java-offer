package LeetCode.test28;

import org.junit.Test;

/*
实现 strStr() 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle
字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
示例 1:
输入: haystack = "hello", needle = "ll"
输出: 2
示例 2:
输入: haystack = "aaaaa", needle = "bba"
输出: -1
 */
public class Test28 {
    @Test
    public void test(){
//       String haystack = "hello";
//       String needle = "ll";
        String haystack = "aaaaa";
        String needle = "bba";
        System.out.println(strStr(haystack, needle));
    }
    public int strStr(String haystack, String needle) {
        int index = haystack.indexOf(needle);
        return index;
    }
}
