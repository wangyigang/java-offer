package LeetCode.test151;


import org.junit.Test;

/*
给定一个字符串，逐个翻转字符串中的每个单词。

示例:

输入: "the sky is blue",
输出: "blue is sky the".
说明:

无空格字符构成一个单词。
输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
进阶: 请选用C语言的用户尝试使用 O(1) 空间复杂度的原地解法。
 */
public class Test151 {
    @Test
    public void test(){
        String str = "the sky is blue";
        System.out.println(reverseWords(str));
    }
    public String reverseWords(String s){

        return "";
    }
//    public String reverseWords(String s) {
//        StringBuilder sb = new StringBuilder();
//        if(s == null || "".equals(s.trim())){
//            return "";
//        }else{
//            String [] strs = s.trim().split("\\s");
//            int len = strs.length-1;
//            for(int i=len;i>=0;i--){
//                if(i == 0){
//                    sb.append(strs[i]);
//                }else{
//                    sb.append(strs[i]+" ");
//                }
//            }
//            return sb.toString();
//        }
//    }

}
