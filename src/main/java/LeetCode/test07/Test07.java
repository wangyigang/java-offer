package LeetCode.test07;

import org.junit.Test;

/*
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:
输入: 123
输出: 321

示例 2:
输入: -123
输出: -321

示例 3:
输入: 120
输出: 21
 */
public class Test07 {
    @Test
    public void test(){
        int x=123;
        System.out.println(reverse(x));
    }
    public int reverse(int x) {
        if(x>Integer.MAX_VALUE){
            return 0;
        }
        String str= x+"";
        String prefix ="";
        if(str.startsWith("+") || str.startsWith("-")){
            prefix = str.substring(0, 1);
            str=str.substring(1);
        }
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        sb.reverse();
        int parseInt=0;
        try {
            parseInt = Integer.parseInt(prefix + sb.toString());
        } catch (NumberFormatException e) {
            parseInt=0;
        }
        return parseInt;
    }
}
