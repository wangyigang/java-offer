package month02.day18;

import org.junit.Test;

public class QuickSort {
    @Test
    public void test(){
        int[] arr = {9, -16, 21, 23, -30, -49, 21, 30, 30};
        //int[] arr = {9, -16, 21};
        quickSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    private void quickSort(int[] arr, int left, int right) {
        if(left>=right){
            return;
        }
        int single = SingleSort(arr, left, right);
        quickSort(arr, left, single-1);
        quickSort(arr, single+1, right);
    }
    private int SingleSort(int[] arr, int left, int right) {
        int key = arr[right];
        while(left<right){
            while(left<right && arr[left]<= key){
                left++;
            }
            arr[right] = arr[left];
            while(left<right && arr[right] >= key){
                right--;
            }
            arr[left] = arr[right];
        }
        arr[left] = key;
        return left; // 返回下标
    }

//
//    /*
//        第一：递归left >right 是否需要 >=
//        第二： 单趟递归的时候查找是 一定要>= 或<=
//     */
//    private void quickSort(int[] arr, int left, int right) {
//        if(left>=right){
//            return ;
//        }
//        int single = SingleSort(arr,left,right);
//        quickSort(arr, left, single-1);
//        quickSort(arr, single+1, right);
//    }
//
//    private int SingleSort(int[] arr, int left, int right) {
//        int key  = arr[right];
//        while(left<right){
//            while(left< right && arr[left]<=key){
//                left++;
//            }
//            arr[right] = arr[left];
//            while(left<right && arr[right]>= key){
//                right--;
//            }
//            arr[left] = arr[right];
//        }
//        arr[left] = key;
//        return left;
//    }

//    /*
//        快速排序思想： 找到一个数，然后调换位置，左边的比他小，右边的比他大，然后递归此方式
//     */
//    public void quickSort(int[] arr, int left, int right){
//        if(left>=right){
//            return ;
//        }
//        int single = SingleSort(arr,left,right);
//        quickSort(arr, left, single-1);
//        quickSort(arr, single+1, right);
//    }
//    //单趟排序
//    private int SingleSort(int[] arr, int left, int right) {
//        int key = arr[right];
//        while(left <right){
//            while (left<right &&arr[left] <=key){
//                left++;
//            }
//            arr[right] = arr[left];
//            while(left<right &&arr[right]>=key){
//                right--;
//            }
//            arr[left] = arr[right];
//        }
//        //一趟排序后
//        arr[left]=key; //此时r==l ,将key赋值给最后一个坑
//        return left;
//    }
}
