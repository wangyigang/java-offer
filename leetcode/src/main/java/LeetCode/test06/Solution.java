package LeetCode.test06;

/*
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:
L     D     R
E   O E   I I
E C   I H   N
T     S     G

(0,0)            (0,3)           (0,6)
(1,0)       (1,2)(1,3)      (1,5)(1,6)
(2,0)  (2,1)     (2,3) (2,4)     (2,6)
(3,0)            (3,3)           (3,6)
 */

import org.junit.Test;

/**
 * 逻辑的转换:  4行：
 * 进行转换：4列 16/6 =
 */
public class Solution {
    @Test
    public void test() {
//        String s = "LEETCODEISHIRING";
        String s = "A";
        int numRows = 2;
        System.out.println(convert(s, numRows));
    }


//    public String convert(String s, int numRows) {
//        if (numRows == 1)
//            return s;
//        char[][] arr = new char[numRows][s.length()];
//
//        int i = 0;
//        int j = 0;
//        //flag  2:竖着走    1：斜着走
//        int flag = 2;
//        for (int k = 0; k < s.length(); k++) {
//            arr[i][j] = s.charAt(k);
//            if (flag == 2)
//                i++;
//            if (i >= numRows) {
//                flag = 1;
//                i = numRows - 1;
//            }
//            if (flag == 1) {
//                i--;
//                j++;
//                if (i < 0) {
//                    i = 1;
//                    flag = 2;
//                }
//            }
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int m = 0; m < numRows; m++) {
//            for (int n = 0; n < s.length(); n++) {
//                if (arr[m][n] >= ' ') {
//                    sb.append(arr[m][n]);
//                }
//            }
//        }
//        return sb.toString();
//    }

    /**
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        char[][] arr = new char[numRows][s.length()];
        int row = 0; //行
        int col = 0;  //列
        int flag = 2;
        for (int k = 0; k < s.length(); k++) {
            arr[row][col] = s.charAt(k);
            if (flag == 2)
                row++;
            if (row >= numRows) {
                flag = 1;
                row = numRows - 1;
            }
            if (flag == 1) {
                row--;
                col++;
                if (row < 0) {
                    row = 1;
                    flag = 2;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < numRows; m++) {
            for (int n = 0; n < s.length(); n++) {
                if (arr[m][n] >= ' ') {  //' '的ascII32,所以大于32的所有字符都可以进入数组中
                    sb.append(arr[m][n]);
                }
            }
        }
        return sb.toString();
    }
}