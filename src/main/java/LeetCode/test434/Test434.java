package LeetCode.test434;

import org.junit.Test;

/*
统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
请注意，你可以假定字符串里不包括任何不可打印的字符。
示例:
输入: "Hello, my name is John"
输出: 5
 */
public class Test434 {
    @Test
    public void test(){
        String s="Hello, my name is John";
        System.out.println(countSegments(s));
    }
    public int countSegments(String s) {
       // s = s.replaceAll("[^a-z]", " ").trim();
        String[] split = s.split(" ");
        int count=0;
        for (String s1 : split) {
            if(!s1.isEmpty()){
                count++;
            }
        }
        return  count;
    }
}
