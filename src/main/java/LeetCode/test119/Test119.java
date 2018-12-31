package LeetCode.test119;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
在杨辉三角中，每个数是它左上方和右上方的数的和。
示例:

输入: 3
输出: [1,3,3,1]
进阶：

你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class Test119 {
    @Test
    public void test() {
        List<Integer> list = getRow(3);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
    public List<Integer> getRow(int rowIndex){
        Integer[] result = new Integer[rowIndex + 1];
        Arrays.fill(result, 0);
        result[0]=1;
        for(int i=1;i<result.length; ++i){
            for(int j=i; j>0; --j){
                result[j]=result[j]+result[j-1];
            }
        }
        return Arrays.asList(result);
    }

//    public List<Integer> getRow(int rowIndex) {
//        Integer[] result = new Integer[rowIndex + 1];
//        Arrays.fill(result, 0);
//        result[0] = 1;
//        for (int i = 1; i < result.length; i++) {
//            for (int j = i; j > 0; j--) {
//                result[j] = result[j] + result[j - 1];
//            }
//        }
//        return Arrays.asList(result);
//    }

}


