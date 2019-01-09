package LeetCode.test520;

import org.junit.Test;

/*
给定一个单词，你需要判断单词的大写使用是否正确。
我们定义，在以下情况时，单词的大写用法是正确的：

全部字母都是大写，比如"USA"。
单词中所有字母都不是大写，比如"leetcode"。
如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
否则，我们定义这个单词没有正确使用大写字母。
 */
public class Test520 {
    @Test
    public void test(){
        System.out.println(detectCapitalUse("USA"));
        System.out.println(detectCapitalUse("leetcode"));
        System.out.println(detectCapitalUse("Google"));
        //System.out.println(detectCapitalUse("GOogle"));
    }

    public boolean detectCapitalUse(String word) {
        if(word.length() == 0) return true;

        int state = -1;
        char[] array = word.toCharArray();
        if(array[0] >= 'a' && array[0] <= 'z') {
            state = 0;
        }
        else if(array[0] >= 'A' && array[0] <= 'Z') {
            state = 1;
        }
        else return false;

        for(int i = 1; i < array.length; i++) {
            switch(state) {
                case 0:
                    if(array[i] >= 'a' && array[i] <= 'z') ;
                    else return false;
                case 1:
                    if(array[i] >= 'a' && array[i] <= 'z')
                        state = 0;
                    else if(array[i] >= 'A' && array[i] <= 'Z'){
                        state = 2;
                    }
                    break;
                case 2:
                    if(array[i] >= 'A' && array[i] <= 'Z') ;
                    else return false;
            }
        }
        return true;
    }
}
