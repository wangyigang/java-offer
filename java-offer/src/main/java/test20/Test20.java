package test20;


import org.junit.Test;

import java.util.ArrayList;

/**
 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，
 如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class Test20 {
    @Test
    public void test(){
        int[][] arr = new int[4][];
        int num=0;
        for(int i=0; i<4; i++){
            arr[i] = new int[4];
            for(int j=0; j<4; j++){
                arr[i][j]= ++num;
            }
        }
        for (int[] ints : arr) {
            for (int i : ints) {
                System.out.println(i);
            }
        }

    }

    //回字形打印
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i=0;

        return arrayList;
    }
}
