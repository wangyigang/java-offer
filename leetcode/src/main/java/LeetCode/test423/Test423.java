package LeetCode.test423;

import org.junit.Test;

/*
给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。
注意:
输入只包含小写英文字母。
输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
输入字符串的长度小于 50,000。
示例 1:
输入: "owoztneoer"
输出: "012" (zeroonetwo)
示例 2:
输入: "fviefuro"
输出: "45" (fourfive)
 */
public class Test423 {
    @Test
    public void test(){
        String string = "owoztneoer";
        System.out.println(originalDigits(string));
    }
    public String originalDigits(String s) {
        int n = s.length();
        int[] count = new int[10];
        for (int i = 0; i < n; i++){
            char c = s.charAt(i);
            if (c == 'z') count[0]++; //只有0有z
            else if (c == 'w') count[2]++; // 2 -> w
            else if (c == 'x') count[6]++; // 6 -> x
            else if (c == 'g') count[8]++; // 8-> g
            else if (c == 'u') count[4]++; // 4 -> u
                //上面是独有的，下面是可以计算出来的
            else if (c == 's') count[7]++; // 6/7 ->s
            else if (c == 'f') count[5]++; // 4/5 -> f
            else if (c == 'h') count[3]++; // 3/8 - > h
            else if (c == 'i') count[9]++; // 5/6/8/9 ->i
            else if (c == 'o') count[1]++; // 0/1/2/4 ->o
        }
        //计算
        count[7] -= count[6];
        count[5] -= count[4];
        count[3] -= count[8];
        count[9] = count[9] - count[8] - count[5] - count[6];
        count[1] = count[1] - count[0] - count[2] - count[4];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; i++){
            for (int j = 0; j < count[i]; j++){
                sb.append(i);
            }
        }
        return sb.toString();
    }

}
