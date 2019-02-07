package test33;

import org.junit.Test;

import java.util.HashMap;

/*
题目描述
在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class Solution {
    @Test
    public void test() {
        String str = "aaabccddee";
        System.out.println(FirstNotRepeatingChar(str));
    }

    public int FirstNotRepeatingChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0 ; i<str.length(); i++){
            char c = str.charAt(i);
            if(map.containsKey(c)){
                Integer cnt = map.get(c);
                cnt++;
                map.put(c, cnt);//count次数+1
            }else{ //没有
                map.put(c, 1);
            }
        }
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(map.get(c)==1){
                return i;
            }
        }
        return -1;
    }


//    public int FirstNotRepeatingChar(String str) {
//        HashMap<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < str.length(); i++) {
//            char c = str.charAt(i);
//            if (map.containsKey(c)) {
//                int time = map.get(c);
//                time++;
//                map.put(c, time);
//
//            } else {
//                map.put(c, 1);
//            }
//        }
//        for (int i = 0; i < str.length(); i++) {
//            char c = str.charAt(i);
//            if (map.get(c) == 1)
//                return i;
//        }
//        return -1;
//    }
}
