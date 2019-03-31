package LeetCode.test08;

import org.junit.Test;

/*
请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

说明：

假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回
INT_MAX (231 − 1) 或 INT_MIN (−231) 。

示例 1:

输入: "42"
输出: 42
示例 2:

输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
示例 3:

输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
示例 4:

输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
示例 5:

输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     因此返回 INT_MIN (−231) 。
 */
@SuppressWarnings("all")
public class Solution {
    @Test
    public void test() {
        String string = "4193 with words";
//        String string = "-91283472332";         //    -91283472332
//        String string = "  0000000000012345678";         //    -91283472332
        System.out.println(myAtoi(string));  //预计输出-2147483648
//        long  i = 91283472332L;
//        System.out.println((int) i);
    }

    //字符转整数
    public int myAtoi(String str) {
        str = str.trim();
        //首先异常情况处理
        if (str == null || str.length() == 0) {
            return 0;
        }
        long result = 0;  //结果值，先记为Long
        //首先判断其实位置的情况
        int start = 0;   //标志起始位置index
        boolean isPositive = false; //标志是否是正数还是负数
        if (str.charAt(0) == '+') {
            start = 1; //数字起始位置+1
            isPositive = true; //正数
        } else if (str.charAt(0) == '-') {
            start = 1;
        } else {
            //起始位置不需要改变
            isPositive = true; //正数
        }
        for (int i = start; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if (tmp >= '0' && tmp <= '9') {
                //将数字进行累加转为long
                result = result * 10 + (tmp - '0');
                if (isPositive) {
                    if (result > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                } else {
                    if (-1 * result < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                }
            } else {
                break;
            }
        }

        //还有符号的情况需要处理
        if (isPositive) {
            return (int) result;
        } else {
            return (int) (-1 * result); //进行转为int
        }
    }
//    public int myAtoi(String str) {
//        str = str.trim(); //去除两侧空格
//        if (str.isEmpty())
//            return 0;
//        int sign = 1; int i = 0;
//        if (str.charAt(0) == '-' || str.charAt(0) == '+'){
//            sign = (str.charAt(0) == '-')? -1 : 1;
//            if (str.length() < 2 || !Character.isDigit(str.charAt(1))) {
//                return 0;
//            }
//            i++;
//        }
//        int n = 0;
//        while (i < str.length()) {
//            if (Character.isDigit(str.charAt(i))) {
//                int d = str.charAt(i) - '0';
//                if (n > (Integer.MAX_VALUE - d) / 10) { //Detect the integer overflow.
//                    n = (sign == -1)? Integer.MIN_VALUE : Integer.MAX_VALUE;
//                    return n;
//                }
//                n = n*10 + d;
//            } else {
//                break;
//            }
//            i++;
//        }
//        return sign * n;
//    }

    //此方式存在Bug,当将下次相乘10+个位数会溢出，所以此时需要进行判断，防止溢出，导致
//    public int myAtoi(String str) {
//        str = str.trim();
//        if (str == null || str.length() == 0) {
//            return 0;
//        }
//        //判断符号位和起始位
//        int start = 0;
//        boolean positiveFlag = false;
//        if (str.charAt(0) == '+') {
//            start = 1;
//            positiveFlag = true;
//        } else if (str.charAt(0) == '-') {
//            start = 1;
//        } else {
//            positiveFlag = true;
//        }
//        long result = 0;
//        for (int i = start; i < str.length(); i++) {
//            char tmp = str.charAt(i);
//            if (tmp >='0' && tmp <= '9') {
//                //-91283472332
//                result = result * 10 + (tmp - '0');
//                //判断是否越界
//                if (positiveFlag) {
//                    if (result > Integer.MAX_VALUE) {
////                        throw new RuntimeException("上溢出...");
//                        return Integer.MAX_VALUE;
//                    }
//                } else {
//                    //所以核心问题还是此处，再即将溢出时的比较，如果小于整数最小值，返回最小值
//                    if (-1*result < Integer.MIN_VALUE) {
////                        throw new RuntimeException("下溢出..");
//                        return Integer.MIN_VALUE;
//                    }
//                }
//            } else {
//                return (int) result; //直接返回0
//            }
//        }
//        //判断符号位
//        if (positiveFlag) {
//            return  (int)result;
//        } else {
//            return (int) (-1 * result);
//
//        }
//    }
}
