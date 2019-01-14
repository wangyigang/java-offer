package test20;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，
 * 如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class Test20 {
    @Test
    public void test() {
//         int[][] arr = new int[4][];
//        int num = 0;
//        for (int i = 0; i < 4; i++) {
//            arr[i] = new int[4];
//            for (int j = 0; j < 4; j++) {
//                arr[i][j] = ++num;
//            }
//        }

//        [[1],[2],[3],[4],[5]]
        int arr[][] = {{1}, {2}, {3}, {4}, {5}};
        //[[1,2],[3,4]]
        //int[][] arr = {{1, 2}, {3, 4}};
        ArrayList<Integer> arrayList = printMatrix(arr);
        for (Integer integer : arrayList) {
            System.out.println(integer);
        }
    }

    //==>3
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        ArrayList<Integer> list = new ArrayList<>();
        while (left <= right && top <= bottom) {
            //从左到y右
            for (int i = left; i <= right; ++i) {
                list.add(matrix[top][i]);
            }
            //从上到下
            for (int i = top+1; i <= bottom; ++i) {
                list.add(matrix[i][right]);
            }
            //从右向左
            if (top != bottom) {
                for (int i = right-1; i >=left;--i) {
                    list.add(matrix[bottom][i]);
                }
            }
            if(left!=right){
                for (int i= bottom-1; i> top; --i){
                    list.add(matrix[i][left]);
                }
            }
            //缩小全
            left++;
            right--;
            top++;
            bottom--;
        }
        return list;

    }


    //==》2
//    public ArrayList<Integer> printMatrix(int[][] matrix) {
//        if (matrix == null) {
//            return null;
//        }
//
//        int left = 0;
//        int top = 0;
//        int right = matrix[0].length - 1; //
//        int bottom = matrix.length - 1;
//
//        ArrayList<Integer> list = new ArrayList<>();
//
//        while (left <= right && top <= bottom) {
//
//            //从左到右
//            for (int i = left; i <= right; i++) {
//                list.add(matrix[top][i]);
//            }
//            //从上到下--二维数组--1为行2为列
//            for (int i = top + 1; i <= bottom; i++) {
//                list.add(matrix[i][right]);
//            }
//            //从右到左
//            //外层if判断过滤只有一行，或一列的场景，上面已经排除，这里需要进行防御性编程，排除异常情况
//            if (top != bottom) {
//                for (int i = right - 1; i >= left; --i) {
//                    list.add(matrix[bottom][i]);
//                }
//            }
//            if (left != right) {
//                for (int i = bottom - 1; i > top; i--) { //最后一个不能使用等于号，因为第一个已放入容器中
//                    list.add(matrix[i][left]);
//                }
//            }
//            //调整四个变量
//            left++;
//            right--;
//            top++;
//            bottom--;
//        }
//        return list;
//    }


//    public ArrayList<Integer> printMatrix(int[][] matrix) {
//        if (matrix == null) {
//            return null;
//        }
//
//        ArrayList<Integer> list = new ArrayList<>();
//        int left = 0, top = 0, rigth = matrix[0].length-1, bottom = matrix.length-1;
//        while (left <= rigth && top <= bottom) {
//
//            //从左到右
//            for(int i=left; i<=rigth; ++i ){
//                list.add(matrix[top][i]);
//            }
//            //从右上到右下
//            for(int i = top+1; i<=bottom; ++i){
//                list.add(matrix[i][rigth]);
//            }
//            //从右到左
//            if(top != bottom){
//                for(int i = rigth-1 ; i>= left ; --i){
//                    list.add(matrix[bottom][i]);
//                }
//            }
//            //从下到上
//            if (left != rigth) {
//                for (int l = bottom - 1; l > top; l--) {
//                    list.add(matrix[l][left]);
//                }
//            }
//
//            left++;
//            top++;
//            rigth--;
//            bottom--;
//        }
//
//        return list;
//    }
//


//    //回字形打印
//    public ArrayList<Integer> printMatrix(int[][] matrix) {
//        if (matrix == null) //防御性编程
//            return null;
//        //ArrayList ：
//        ArrayList<Integer> list = new ArrayList<>();
//        //row行数
//        int row = matrix.length;
//        //列数
//        int col = matrix[0].length;
//        //上下左右--左上是0， 右下是数组长度,
//        int left = 0, top = 0, right = col - 1, bottom = row - 1;
//        //
//        while (left <= right && top <= bottom) {
//            //从左向右
//            //从左到右：
//            for (int i = left; i <= right; i++) {
//                list.add(matrix[top][i]);
//            }
//            //从上到下（从下一行开始向下走）
//            for (int j = top + 1; j <= bottom; j++) {
//                list.add(matrix[j][right]);
//            }
//            //从右到左
//            if (top != bottom) {
//                for (int k = right - 1; k >= left; k--) {
//                    list.add(matrix[bottom][k]);
//                }
//            }
//            //从下到上
//            if (left != right) {
//                for (int l = bottom - 1; l > top; l--) {
//                    list.add(matrix[l][left]);
//                }
//            }
//
//            //下一个正方形矩阵
//            top++;
//            left++;
//            right--;
//            bottom--;
//
//        }
//        return list;
//    }
}
