package month02.day18;

import org.junit.Test;

public class BubbleSort {
    @Test
    public void test(){
        int[] data = { 9, -16, 21, 23, -30, -49, 21, 30, 30 };
        bubbleSort(data, 0,data.length-1);
        for (int num : data) {
            System.out.print(num+" ");
        }
    }
    public void bubbleSort(int[] arr, int left, int right){
        for(int i=0; i<arr.length-1; i++){
            for(int j=0; j<arr.length-i-1; j++){
                if(arr[j] >arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }

}
