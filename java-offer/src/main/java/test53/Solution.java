package test53;

        import org.junit.Test;

/*
题目描述
请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"
和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class Solution {
    @Test
    public void test() {
        String str = "+100";
        String str2 = "5e2";
        String str3 = "-123";
        String str4 = "3.1416";
        System.out.println(isNumeric(str.toCharArray()));
        System.out.println(isNumeric(str2.toCharArray()));
        System.out.println(isNumeric(str3.toCharArray()));
        System.out.println(isNumeric(str4.toCharArray()));
    }

    public boolean isNumeric(char[] str) {
        return String.valueOf(str).matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
    }
//    private boolean isNumeric(char[] str) {
//        //match匹配正则表达式
//        /*
//            ?表示0次或1次
//            * 匹配零次或多次。要匹配 * 字符，请使用 \*。
//            (): 表示子表达式
//         */
//       // return String.valueOf(str).matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
//        return String.valueOf(str).matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
//    }

    /*正则表达式前面
        前面符号位+-
        数字[0-9]
        .小数点
        [0-9]
        [eE]
        后面是+-
        数字0-9
     */
//    public boolean isNumeric(char[] str) {
//        return String.valueOf(str).matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
//    }

}