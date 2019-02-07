package test32;

import org.junit.Test;

/*
把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class Soulution {
    @Test
    public void test() {
        System.out.println(GetUglyNumber_Solution(4));
    }


    public int GetUglyNumber_Solution(int index) {
        //异常情况处理
        if (index <= 0)
            return 0;
        //创建一个数组
        int[] result = new int[index];
        int count = 0;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        result[0] = 1;
        int tmp = 0;
        while (count < index - 1) {
            tmp = min(result[i2] * 2, result[i3] * 3, result[i5] * 5);
            if (tmp == result[i2] * 2) i2++;//三条if防止值是一样的，不要改成else的
            if (tmp == result[i3] * 3) i3++;
            if (tmp == result[i5] * 5) i5++;
            result[++count] = tmp;
        }
        return result[index - 1];
    }

    /**
     *  返回三者之中最小值
     * @param a
     * @param b
     * @param c
     * @return
     */
    private int min(int a, int b,int c) {
        int tmp= (a > b) ? b : a;
        return tmp>c? c : tmp;
    }


//    public int GetUglyNumber_Solution(int index) {
//        //考虑异常情况
//        if (index <= 0) {
//            return 0;
//        }
//        int number =0;
//        int uglyFount=0;
//        while (uglyFount<index){
//            ++number;
//            if(isUglyFound(number)){
//                ++uglyFount;
//            }
//        }
//        return number;
//    }
//
//    private boolean isUglyFound(int number) {
//        while(number %2==0)
//            number/=2;
//        while(number%3==0)
//            number/=3;
//        while (number%5==0)
//            number/=5;
//
//        return (number==1)?true:false;
//    }
}
