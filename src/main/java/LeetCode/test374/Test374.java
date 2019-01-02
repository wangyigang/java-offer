package LeetCode.test374;

import org.junit.Test;

import javax.swing.text.Highlighter;

/*
我们正在玩一个猜数字游戏。 游戏规则如下：
我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
每次你猜错了，我会告诉你这个数字是大了还是小了。
你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：

-1 : 我的数字比较小
 1 : 我的数字比较大
 0 : 恭喜！你猜对了！
示例 :

输入: n = 10, pick = 6
输出: 6
 */
public class Test374 {
    @Test
    public void test(){
        int  n =10;
        System.out.println(guessNumber(n));
    }
    public int guessNumber(int n) {
        int left = 1;int right = n;
        int mid = left+ (right-left)/2;
        while (left<=right){
            if(guess(mid)==1){
                left=mid+1;
                mid=left+(right-left)/2;
            }else if(guess(mid)==-1){
                right=mid-1;
                mid=left+(right-left)/2;
            }else{
                return mid;
            }
        }
        return 1;
    }
    public int guess(int num){
        return 0;
    }
}
