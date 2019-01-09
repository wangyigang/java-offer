package test01;

import org.junit.Test;

import java.util.Vector;

/*
题目描述
在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
判断数组中是否含有该整数。
 */
public class Test01 {
    @Test
    public void  test(){

    }
    public boolean Find(int target, int [][] array) {
        int row=array.length;
        int col=array[0].length;
        int m=0,n=col-1;
        while(n>=0&&m<row){
            if(array[m][n]>target){
                n--;
            }
            else if(array[m][n]<target){
                m++;
            }
            else
                return true;
        }
        return false;

    }
}
