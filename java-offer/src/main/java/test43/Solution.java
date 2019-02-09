package test43;

import org.junit.Test;

public class Solution {
    @Test
    public void test(){
        String str="abcXYZdef";
        String s = LeftRotateString(str, 3);
        System.out.println(s);
    }
    public String LeftRotateString(String str,int n) {
        return (str == null || str.length() ==0)? "" : (str.substring(n)+str.substring(0, n));
    }
}