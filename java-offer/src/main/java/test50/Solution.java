package test50;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
题目描述
在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，
但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。

 */
public class Solution {
    @Test
    public void test() {
        int[] ints = {2, 1, 3, 1, 4};
        int[] ret = new int[1];
        System.out.println(duplicate(ints, ints.length, ret));
    }

    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false

    /**
     *  注意点： 一定要注意防御性编程  2. 注意提议，duplication是需要返回的, true和false的含义不同
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        //防御性编程
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : numbers) {
            if (map.containsKey(num)) {
                Integer cnt = map.get(num);
                map.put(num, cnt + 1);
            } else { //map中还没有
                map.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 2) {
                duplication[0] = entry.getKey();
                return true;
            }
        }
        return false;
    }
}
