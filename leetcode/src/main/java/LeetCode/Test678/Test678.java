package LeetCode.Test678;

import org.junit.Test;

import java.util.Stack;


/*
给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。
有效字符串具有如下规则：
任何左括号 ( 必须有相应的右括号 )。
任何右括号 ) 必须有相应的左括号 ( 。
左括号 ( 必须在对应的右括号之前 )。
* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
一个空字符串也被视为有效字符串。
 */
public class Test678 {
    private Stack<Integer> left = new Stack();
    private Stack<Integer> star = new Stack();
    @Test
    public void test(){
//        System.out.println(checkValidString("()"));
//        System.out.println(checkValidString("(*)"));
//        System.out.println(checkValidString("(*))"));
        System.out.println(checkValidString("(*()"));
        System.out.println(checkValidString("(((******))"));
    }
    //利用栈的特性进行处理
    public boolean checkValidString(String s) {
        for(int i=0;i<s.length(); ++i){
            if(s.charAt(i)=='('){
                left.push(i);
            }else if(s.charAt(i) == '*'){
                star.push(i);
            }else{//右括号
                if(left.size()>0){
                    left.pop();
                }else if(star.size()>0){
                    star.pop();
                }
            }
        }

        while (left.empty() == false && star.empty() == false) {
            int leftI = left.pop();
            int starI = star.pop();
            if (starI < leftI) {
                return false;
            }
        }
        return left.empty() == true;
    }
}


