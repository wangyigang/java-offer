package test31;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
题目描述
输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
则打印出这三个数字能排成的最小数字为321323。
 */
public class Solution {
    @Test
    public void test(){
        int[] arr = {3,32,321};
        System.out.println(PrintMinNumber(arr));
    }
    public String PrintMinNumber(int [] numbers) {
        String str="";
        ArrayList<String> list = new ArrayList<>();
        for (int number : numbers) {
            list.add(number+"");
        }
        Collections.sort(list, new Comparator<String>() {
            //比较器的目的是：超出字符串拼接成的字符比较小的，数字本身小，但并不代表，拼接而成的字符串小
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1+o2;
                String s2= o2+o1;
                return s1.compareTo(s2);
            }
        });


        for (String s : list) {
            str+= s;
        }
        return str;
    }


//    public String PrintMinNumber(int [] numbers) {
//        String str= "";
//        ArrayList<String> list =  new ArrayList<>();
//        for (int number : numbers) {
//            list.add(number+"");
//        }
//        Collections.sort(list, new Comparator<String>() {
//            //实现一个比较器,比较两个拼接的字符串哪个大，那个小--要的是拼接而成的字符串小
//            @Override
//            public int compare(String o1, String o2) {
//                String s1 = o1+""+o2;
//                String s2 = o2+""+o1;
//                return s1.compareTo(s2);
//            }
//        });
//
//        for (String s : list) {
//            str+=s;
//        }
//        return str;
//    }
}