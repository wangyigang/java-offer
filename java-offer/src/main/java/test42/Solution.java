package test42;


/*
题目描述
小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,
他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:
18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!

输出描述:
输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
import org.junit.Test;

import java.util.ArrayList;
public class Solution {
    @Test
    public void test(){
        ArrayList<ArrayList<Integer>> list = FindContinuousSequence(100);
        for (ArrayList<Integer> integers : list) {
            for (Integer integer : integers) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }
    //==>2
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if(sum<3){ //连续正整数的和必须大于等于3,所以不过小于3表示错误数据
            return list;
        }
        //small 和big两个指针方式
        int small = 1;
        int big = 2;
        int curSum = small+big;
        int middle= (1+sum)/2;
        while(small <middle){
            //进行一次判断，判断sum 和此时的curSum是否相同
            insertIntoList(sum, list, small, big, curSum);

            while(curSum> sum && small < middle){ //数字综合大的范围
                //1234 10 此时已经大于sum ,所以将第一位的small减去，然后small++;
                curSum-= small; //这两处不能调换位置，否则出错
                small++;
                insertIntoList(sum, list, small, big, curSum);
            }
            big++;
            curSum+= big;

        }
        return list;
    }


//
//    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
//        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
//        //防御性编程
//        if(sum<3){
//            return list;
//        }
//        int small = 1;
//        int big = 2;
//        int middle = (1+sum)/2;
//        int curSum = small+big;
//        while(small < middle){ //整体循环结束的标志，因为small不能比middle大，
//            //判断两个数字是否相同，相同将范围内的数字全部放入list中
//            insertIntoList(sum, list, small, big, curSum);
//            while(curSum >sum  && small <middle){ //范围内的数字，全部相加，所以用while
//                curSum-= small;
//                small++;
//
//                insertIntoList(sum, list, small, big, curSum);
//            }
//            //小于的情况
//            big++;
//            curSum+=big;
//        }
//
//        return list;
//    }

    private void insertIntoList(int sum, ArrayList<ArrayList<Integer>> list, int small, int big, int curSum) {
        if(curSum == sum){
            ArrayList<Integer> integers = new ArrayList<>();
            for(int i= small; i<= big; i++){
                integers.add(i);
            }
            list.add(integers);
        }
    }
}