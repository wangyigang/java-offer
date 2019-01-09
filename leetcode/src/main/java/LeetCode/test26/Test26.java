package LeetCode.test26;

import org.junit.Test;
/*
给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

示例 1:
给定数组 nums = [1,1,2],
函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
你不需要考虑数组中超出新长度后面的元素。
示例 2:
给定 nums = [0,0,1,1,1,2,2,3,3,4],
函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
你不需要考虑数组中超出新长度后面的元素。
说明:
为什么返回数值是整数，但输出的答案是数组呢?
请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
你可以想象内部操作如下:
// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
 */

public class Test26 {
    @Test
    public void test(){
        //int[] arr = new int[]{1,1,2};
//        int[] arr = new int[]{0,0,1,1,1,2,2,3,3,4}; //5
        int[] arr = new int[]{1,1,2};
        System.out.println(removeDuplicates(arr));
        for (int i : arr) {
            System.out.println(i);
        }
    }
    //去除重复元素
    //去除重复元素
    public int removeDuplicates(int[] nums) {
        int count = nums.length; //首先求出数组长度
        int i, j;
        for(i=0, j=1; j<count; j++)//遍历数组
            if(nums[j] != nums[i]) //如果数组两个之间数字不相同
                nums[++i] = nums[j];//将不相同的数字全部放在i的后面，其余的不管
        return i+1;//最后返回i+1==表示不同数字的个数
    }
}
