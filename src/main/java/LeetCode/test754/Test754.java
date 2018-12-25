package LeetCode.test754;

import org.junit.Test;

/*
在一根无限长的数轴上，你站在0的位置。终点在target的位置。
每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。
返回到达终点需要的最小移动次数。
示例 1:
输入: target = 3
输出: 2
解释:
第一次移动，从 0 到 1 。
第二次移动，从 1 到 3 。

示例 2:
输入: target = 2
输出: 3
解释:
第一次移动，从 0 到 1 。
第二次移动，从 1 到 -1 。
第三次移动，从 -1 到 2
 */
public class Test754 {
    @Test
    public void test() {
        System.out.println(reachNumber(3)); //2
        System.out.println(reachNumber(2));//3
    }
    public int reachNumber(int target) {
        //处理特殊情况--target为负数情况
        target = Math.abs(target);
        int sum = 0;
        int count = 0;
        while (sum<target){
            count++;
            sum+= count;
        }
        //此时sum > target
        if((sum-target) %2!=0){ //基数步情况
            if(count %2==0){ //次数是偶数，步数也是偶数，在走一次即可
                count++;
            }else{ //次数是基数，步数也是基数，再走两次即可
                count+=2;
            }
        }
        return count;
    }

//    public int reachNumber(int target){
//        //处理target的负数情况
//        target = Math.abs(target);
//        int sum=0;
//        int count =0;
//        while (sum<target){
//            count++;
//            sum+=count;
//        }
//        //剩余情况
//        if((sum-target)%2 !=0){ //基数步
//            if(count %2==0){
//                count+=1;//基数步加1=偶数不
//            }else{
//                count+=2;
//            }
//        }
//        return count;
//    }

//    public int reachNumber(int target) {
//        //处理target为负数的情况,将负值转为正值
//        target = Math.abs(target);
//        int count = 0;
//        int sum = 0;
//        while (sum < target) {
//            count++;
//            sum += count;
//        }
//        //
//        if ((sum - target) % 2 != 0) {
//            if (count % 2 == 0) {
//                //当前次数是基数还是偶数，因为次数和步数相同
//                count += 1;
//            } else { //基数次数+2
//                count += 2;
//            }
//        }
//        return count;
//    }
}
