package LeetCode.Test229;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/*
给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
示例 1:
输入: [3,2,3]
输出: [3]
示例 2:
输入: [1,1,1,3,3,2,2,2]
输出: [1,2]
 */
public class Test229 {
    //最多有两个数字超过1/3的次
    @Test
    public void test(){
        int[] arr = {3,2,3};

        List<Integer> list = majorityElement(arr);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    /*
    暗含条件：一串数字中超过1/3的概率的数字只会有两个
    所以，可以只用两个变量记录即可
    cnt1, cnt2 两个变量分别记录次数
    candidate1 candidate2 分别记录两个出现频率次数最多的两个数字的下标
     */
    public List<Integer> majorityElement(int[] nums) {
        int n=nums.length;
        int cnt1=0, cnt2=0;
        //如果candidate1,2都置为0，若nums=[0,0,0]会重复计入！！！
        int candidate1=0, candidate2=1;
        for(int i=0;i<n;i++) {//(1)找出出现次数最多的2个元素
            //每次存储2个元素,如果第3个元素与其中1个相同,则增加计数cnt1或cnt2,
            //否则,清除candidate1和candidate2,从下一个数据开始重新统计.
            if(candidate1 == nums[i]) {
                cnt1++;
            }
            else if(candidate2 == nums[i]){
                cnt2++;
            }else if(cnt1 == 0) {
                candidate1=nums[i];
                cnt1=1;
            }else if(cnt2 == 0) {
                candidate2=nums[i];
                cnt2=1;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        //(2)验证这2个元素是否满足条件,即出现的次数是否大于n/3;
        cnt1=0;cnt2=0;
        for(int i=0;i<n;i++) {
            if(nums[i] == candidate1) cnt1++;
            if(nums[i] == candidate2) cnt2++;
        }
        List<Integer> res = new ArrayList<>();
        if(cnt1 > n/3) res.add(candidate1);
        if(cnt2 > n/3) res.add(candidate2);
        return res;
    }
}
