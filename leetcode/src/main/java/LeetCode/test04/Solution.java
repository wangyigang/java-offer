package LeetCode.test04;

import org.junit.Test;

/*
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5
 */
public class Solution {
    @Test
    public void test() {
        int[] nums1 = new int[]{1, 2, 3, 4, 5,6,7,8};
        int[] nums2 = new int[]{3, 4};
//        int[] nums1 = new int[]{1, 3};
//        int[] nums2 = new int[]{2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    /**
     * @param A
     * @param B
     * @return
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length; //获取数组A的长度
        int n = B.length; // 获取数组B的长度
        if (m > n) { // 如果A 的长度大于B的长度，就进行交换，使m指向长度小的数组
            int[] temp = A;
            A = B;
            B = temp; //数组内容交换
            int tmp = m;
            m = n;
            n = tmp;  //数组长度进行交换
        }
        int iMin = 0;
        int iMax = m; //较小的数组的长度
        int halfLen = (m + n + 1) / 2; //获取两个数组长度的平均值
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i; //平均长度减去 较小数组的平均长度
            if (i < iMax && B[j - 1] > A[i]) { //i 太小
                iMin = i + 1;
            } else if (i > iMin && A[i - 1] > B[j]) { //i太大
                iMax = i - 1;
            } else { //合适
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) { //奇数个元素，直接返回
                    return maxLeft;
                }
                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }
                return (maxLeft + minRight) / 2.0;  //二分法逼近思想--同时查找两个数组，找到中间位置的
            }
        }
        return 0.0;
    }


    /**
     * 获取中间变量:从两个数组中  --时间复杂度O(log(m+n))
     * @param A
     * @param B
     * @return
     */
//    public double findMedianSortedArrays(int[] A, int[] B) {
//        int m = A.length;
//        int n = B.length;
//        if (m > n) { // m指向小的数组， n 指向大的数组
//            int[] temp = A;
//            A = B;
//            B = temp; //数组内容交换
//            int tmp = m;
//            m = n;
//            n = tmp;  //数组长度进行交换
//        }
//        //
//        int iMin = 0;
//        int iMax = m;  //
//        int halfLen = (m + n + 1) / 2;   //m+n+1/2指向中间值
//        while (iMin <= iMax) {
//            int i = (iMin + iMax) / 2;
//            int j = halfLen - i;  //最后一个
//            if (i < iMax && B[j - 1] > A[i]) {
//                iMin = i + 1; // i is too small
//            } else if (i > iMin && A[i - 1] > B[j]) {
//                iMax = i - 1; // i is too big
//            } else { // i is perfect
//                int maxLeft = 0;
//                if (i == 0) {
//                    maxLeft = B[j - 1];
//                } else if (j == 0) {
//                    maxLeft = A[i - 1];
//                } else {
//                    maxLeft = Math.max(A[i - 1], B[j - 1]);
//                }
//                if ((m + n) % 2 == 1) {
//                    return maxLeft;
//                }
//
//                int minRight = 0;
//                if (i == m) {
//                    minRight = B[j];
//                } else if (j == n) {
//                    minRight = A[i];
//                } else {
//                    minRight = Math.min(B[j], A[i]);
//                }
//                return (maxLeft + minRight) / 2.0;
//            }
//        }
//        return 0.0;
//    }
}