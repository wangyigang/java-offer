package month02.day18;

import org.junit.Test;

public class binarySearchTest{
    @Test
    public void test(){
        int[] arr = new int[]{1,3,5,7,9,10};
        System.out.println(binarySearch(arr, 5));
    }

    private int binarySearch(int[] arr, int num) {
        int left =0;
        int right = arr.length-1;
        int mid = (left+right)/2;
        while(left<=right){
            if(arr[mid] == num){
                return mid;
            }else if(num > arr[mid] ){
                left=mid+1;
                mid = (left+right)/2;
            }else{
                right=mid-1;
                mid = (left+right)/2;
            }
        }
        return -1;
    }
}
