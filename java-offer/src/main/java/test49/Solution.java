package test49;


/*

题目描述
将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
输入描述:
输入一个字符串,包括数字字母符号,可以为空
输出描述:
如果是合法的数值表达则返回该数字，否则返回0
示例1
输入
复制
+2147483647
1a33
输出
复制
2147483647
0
 */

import org.junit.Test;

public class Solution {
    @Test
    public void test() {
        System.out.println(StrToInt("+2147483647"));
        System.out.println(StrToInt("1a33"));

    }
    public int StrToInt(String str) {
        //防御性编程
        if(str ==null || str.length() ==0){
            return 0;
        }
        //判断符号位和起始位
        int start =0;
        boolean positiveFlag = false;
        if(str.charAt(0) == '+'){
            start=1;
            positiveFlag= true;
        }else if(str.charAt(0) =='-'){
            start=1;
        }else{
            positiveFlag = true;
        }
        long result=0;
        for(int i=start; i<str.length(); i++){
            char tmp = str.charAt(i);
            if(tmp>'0' && tmp<'9'){
                //
                result= result*10+tmp-'0';
                //判断是否越界
                if(positiveFlag){
                    if(result>Long.MAX_VALUE){
                        throw new RuntimeException("上溢出...");
                    }
                }else {
                    if(result< Long.MIN_VALUE){
                        throw new RuntimeException("下溢出..");
                    }
                }
            }else{
                return 0; //直接返回0
            }
        }
        //判断符号位
        if(positiveFlag){
            return (int) result;
        }else{
            return (int) (-1*result);
        }
    }


    /*
        两个错误：一：测试数据有误:数字+""会将+去掉
                 二： 正常数字情况下 ：符号位标逻辑错误positiveFlag
     */
    //=>1
//    public int StrToInt(String str) {
//        //防御性编程
//        if (str == null || str.length() == 0) {
//            return 0;
//        }
//        //常规性编程，首先判断首位是否是符号位
//        int start = 0; //起始位
//        boolean positiveFlag = false; //判断是否是整数
//        if (str.charAt(0) == '+') {
//            positiveFlag = true;
//            start = 1;
//        } else if (str.charAt(0) == '-') {
//            start = 1;
//        } else {
//            //正常情况，首位是正数,起始位为0，
//            positiveFlag = true;
//        }
//        //long接收，判断是否溢出的情况
//        long result = 0;
//        for (int i = start; i < str.length(); i++) {
//            //判断是否符合是数字 0-9
//            char tmp = str.charAt(i);
//            if (tmp > '0' && tmp < '9') {
//                result = result * 10 + tmp - '0';
//                if (positiveFlag) {
//                    //正数，判断是否超过integer的正数最大值
//                    if (result > Integer.MAX_VALUE) {
//                        throw new RuntimeException("上溢出...");
//                    }
//                } else { //负数
//                    if (result * -1 < Integer.MIN_VALUE) {
//                        throw new RuntimeException("下溢出...");
//                    }
//                }
//            } else { //之中包含非数字0-9
//                return 0;
//            }
//        }
//
//        //最后根据正负号判断
//        if (positiveFlag) {
//            return (int) result;
//        } else {
//            return (int) (-1 * result);
//        }
//
//    }

    //要求不能使用字符串转换整数的库函数
    /*
        判断首位符号
            首位是+ 省略
        位号是否是数字0-9，如果不是0-9的数字，直接返回0
     */
//    public int StrToInt(String str) {
//        //防御性编程
//        if (str == null || str.length() == 0) {
//            return 0;
//        }
//
//        int start; //起始位
//        int tag;  //标志位
//        if (str.charAt(0) == '+') {
//            tag = 1;
//            start = 1;
//        } else if (str.charAt(0) == '-') {
//            tag = 0;
//            start = 1;
//        } else {
//            tag = 1;
//            start = 0;
//        }
//        long result = 0;
//        for (int i = start; i < str.length(); i++) {
//            char tmp = str.charAt(i);
//            if (tmp >= '0' && tmp <= '9') {
//                result = result * 10 + (tmp - '0'); //*10 + tmp --循环累加
//                if (tag == 1 && result > Integer.MAX_VALUE)
//                    throw new RuntimeException("上溢出");
//                if (tag == 0 && result < Integer.MIN_VALUE)
//                    throw new RuntimeException("下溢出");
//            } else { //数字中包含非数字位，则证明错误，直接返回0
//                return 0;
//            }
//        }
//
//        if (tag == 0)
//            return (int) (-1 * result);
//        else {
//            return (int) result;
//        }
//
//    }
}