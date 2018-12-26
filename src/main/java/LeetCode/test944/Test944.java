package LeetCode.test944;

import org.junit.Test;

/*
给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。 所余下的字符串行从上往下读形成列。
比如，有 A = ["abcdef", "uvwxyz"]，删除索引序列 {0, 2, 3}，删除后 A 为["bef", "vyz"]，
 A 的列分别为["b","v"], ["e","y"], ["f","z"]。（形式上，第 n 列为 [A[0][n], A[1][n], ..., A[A.length-1][n]]）。
假设，我们选择了一组删除索引 D，那么在执行删除操作之后，A 中所剩余的每一列都必须是 非降序 排列的，然后请你返回 D.length 的最小可能值。

示例 1：
输入：["cba", "daf", "ghi"]
输出：1
解释：
当选择 D = {1}，删除后 A 的列为：["c","d","g"] 和 ["a","f","i"]，均为非降序排列。
若选择 D = {}，那么 A 的列 ["b","a","h"] 就不是非降序排列了。

示例 2：
输入：["a", "b"]
输出：0
解释：D = {}

示例 3：
输入：["zyx", "wvu", "tsr"]
输出：3
解释：D = {0, 1, 2}
 */
public class Test944 {
    @Test
    public void test() {
        String[] arr1 = new String[]{"cba", "daf", "ghi"}; //1
        String[] arr2 = new String[]{"a", "b"};  //0
        String[] arr3 = new String[]{"zyx", "wvu", "tsr"}; //3
        System.out.println(minDeletionSize(arr1));
        System.out.println(minDeletionSize(arr2));
        System.out.println(minDeletionSize(arr3));
    }
    public int minDeletionSize(String[] A){
        int count =0;
        for(int i=0; i<A[0].length(); ++i){
            for(int j=0; j<A.length-1; ++j){
                if(A[j].charAt(i) >A[j+1].charAt(i)){
                    count++;
                    break;
                }
            }
        }
        return  count;
    }

    /*
    题目很难理解，读了好几遍才有所理解意思，求出删除下标的列，使得数组中剩下的元素，对应下标中的值
    组成的字符串不是降序的
     */
//    public int minDeletionSize(String[] A) {
//        int count =0;
//        for(int i=0; i<A[0].length(); ++i){
//            char left = A[0].charAt(i);
//            for(int j=1; j<A.length; ++j){
//                char cur = A[j].charAt(i);
//                if(left <= cur){
//                    left = cur;
//                }else {
//                    count++;
//                    break;
//                }
//            }
//        }
//        return count;
//    }
}
