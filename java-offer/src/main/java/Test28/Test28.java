package Test28;

import org.jruby.ast.IterNode;
import org.junit.Test;

import java.util.Arrays;

/*
题目描述
数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如
果不存在则输出0。
 */
public class Test28 {
    @Test
    public void test() {
        int[] arr = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
        int i = MoreThanHalfNum_Solution(arr);
        System.out.println(i);
    }

    //=>2
    public int MoreThanHalfNum_Solution(int[] array) {
        //先找到，然后判定是不是
        int count = 1;
        int tmp = array[0];
        for (int i = 0; i < array.length; i++) {
            if (tmp == array[i]) {
                count++;
            } else if (count > 0) { //不相等，次数>0 ，表示有重复的,
                count--;
            } else {
                tmp = array[i];
                count = 1;
            }
        }
        count=0;
        for(int i=0; i<array.length; i++){
            if(tmp== array[i]){
                count++;
            }
        }
        return count >array.length/2?tmp:0; //最后进行判定，是否是正确的
    }

//    //=>1
//    public int MoreThanHalfNum_Solution(int[] array) {
//        int count =0;
//        int tmp =0;
//        //先找到超过一半的数--正常情况下，能够找到，非正常情况下，tmp是最后最后的一个数，也不是超过一半的数
//        for(int i=0; i<array.length; i++){
//            if(tmp == array[i]){
//                count++;
//            }else if(count>0 ){
//                count--;
//            }else{
//                tmp= array[i];
//                count=1;
//            }
//        }
//        System.out.println("tmp="+tmp);
//        System.out.println("count="+count);
//
//        count=0;
//        for(int i=0; i<array.length; i++){
//            if(tmp == array[i]){
//                count++;
//            }
//        }
//        return count>array.length/2? tmp:0;
//    }


    //利用题意的规则：进行判断，先进行排序，然后判断中间值的那个是否正确
    //时间复杂度N*log(N)
//    public int MoreThanHalfNum_Solution(int[] array) {
//        if(array.length <1){
//            return 0;
//        }
//        int count = 0;
//        Arrays.sort(array);
//        int num = array[array.length/2];
//        for(int i=0; i<array.length; i++){
//            if(num == array[i])
//                count++;
//        }
//        if(count<= (array.length/2)){
//            num=0;
//        }
//        return num;
//    }
}
