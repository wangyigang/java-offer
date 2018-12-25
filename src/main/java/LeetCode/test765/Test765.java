package LeetCode.test765;

import org.junit.Test;


/*
765. 情侣牵手
N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。
 一次交换可选择任意两人，让他们站起来交换座位。

人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。

这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。

示例 1:
输入: row = [0, 2, 1, 3]
输出: 1
解释: 我们只需要交换row[1]和row[2]的位置即可。
示例 2:

输入: row = [3, 2, 0, 1]
输出: 0
解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 */
public class Test765 {
    @Test
    public void test() {
//        int[] arr = new int[]{3, 2, 0, 1};
        int[] arr = new int[]{2,0,5,4,3,1}; //1 ==>1和2进行交换
        System.out.println(minSwapsCouples(arr));
    }
    public int minSwapsCouples(int[] row){
        int sum=0;
        int mate = 0; //记录对应的配偶
        int count =0;

        for(int i=0; i<row.length; ++i){
            //先判断奇偶
            if(row[i] %2==0){
                //如果是偶数,那么配偶是右边==》+1
                mate = row[i] +1;
            }else{
                //如果是基数，那么配有在左边==》-1
                mate = row[i] -1;
            }
            for(int j=i+1; j<row.length; ++j){
                //从后找和他配对的，但是不是相邻的
                if(row[j] == mate && j != (i+1)){
                    //进行交换
                    int tmp = row[i+1];
                    row[i+1] = row[j];
                    row[j] = tmp;

                    //记录次数
                    count++;
                }
            }

        }
        return count;
    }






//    public  int minSwapsCouples(int[] row){
//        int sum=0;
//        int mate  = 0; //求对应的配偶
//        int count =0;
//        //遍历数组
//        for (int i = 0; i < row.length; i++) {
//            //先判断是否是情侣的男和女
//            if(row[i]%2 ==0){//偶数
//                mate= row[i]+1; //row[i]对应数的+1
//            }else{//基数
//                mate =row[i]-1;//row[i]对应数-1
//            }
//            //内层循环是总结最小次数
//            for (int j = i+1; j < row.length; j++) { // 从i后面的数进行查询
//                if(row[j] == mate && j!= (i+1)){ //j是他的夫妻，并且i和j 没有挨在一起
//                    //进行交换
//                    int tmp = row[j];
//                    row[j] = row[i+1];
//                    row[i+1] = tmp;
//                    //增加count次数
//                    count++; //交换一次，记录一次
//                }
//            }
//        }
//        return count;
//    }


//    public int minSwapsCouples(int[] row) {
//        int sum = 0;
//        //遍历row数组
//        for (int i = 0; i < row.length; i++) {
//            int mate; //mate是i位置人的配偶序号
//            if (row[i] % 2 == 0){ //配偶是0,配偶则是1
//                mate = row[i] + 1;
//            }
//            else {//如果第一个是基数，第二个是偶数(基数-1)
//                mate = row[i] - 1;
//            }
//            for (int j = i + 1; j < row.length; j++) {
//                //
//                if (row[j] == mate && j != (i + 1)) { //配偶不在i旁边
//                    int tmp = row[j];
//                    row[j] = row[i + 1];
//                    row[i + 1] = tmp;
//                    sum++;
//                    break;
//                }
//            }
//        }
//        return sum;
//
//    }
}
