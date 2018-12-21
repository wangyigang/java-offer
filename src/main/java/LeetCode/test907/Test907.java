package LeetCode.test907;

import org.junit.Test;
/*
给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。
输入：[3,1,2,4]
子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
输出：17
 */
public class Test907 {
    @Test
    public void test(){
        int[] arr=new int[]{3,1,2,4};
        System.out.println(sumSubarrayMins(arr));
    }
    public int sumSubarrayMins(int[] A) {
        int sum=0;
        for(int i=1; i<=A.length; ++i){
            int tmpMin=0;
            int num=i;
            //先拆分数据
            for(int j=0; j<A.length && num>0; ++j, --num){
                //从数据中找出最小
                tmpMin = A[j];
                if(tmpMin>A[j]){
                    tmpMin=A[j];
                }
                //相加求和
            }
            System.out.println(tmpMin);
            sum+=tmpMin;
        }
        return sum;
    }
}
