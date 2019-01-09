package Test06;

public class Solution {
    public int minNumberInRotateArray(int [] array) {
        int min=array[0];
        for (int i : array) {
            if(min>i){
                min =i;
            }
        }
        return min;
    }
}