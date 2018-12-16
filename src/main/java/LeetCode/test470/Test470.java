package LeetCode.test470;

import org.junit.Test;
/*
已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，
试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。

不要使用系统的 Math.random() 方法。


这是一道概率题 计算两个rand7()和的个位，两个数用a b表示，即( rand7() + rand7() ) % 10

枚举如下：
	a	1	2	3	4	5	6	7
b
1		2	3	4	5	6	7	8
2		3	4	5	6	7	8	9
3		4	5	6	7	8	9	0
4		5	6	7	8	9	0	1
5		6	7	8	9	0	1	2
6		7	8	9	0	1	2	3
7		8	9	0	1	2	3	4
去掉右上角的
6	7	8
7	8	9
8	9	0      后

每个数字的出现次数为4次，0-9的概率相同

所以程序思路就很明了,当结果扫到右上角的时候进行递归调用，直到输出其他结果
    a=rand7();  b=rand7();
 if(a>4&&b<4)  return rand10();
 else          return (a+b)%10+1;


平均调用2.3次rand7()
 */
public class Test470 {
    @Test
    public void test(){
        //通过random 7 实现random 10

    }
    /*
    public int rand10() {
        int a=rand7();
        int b=rand7();
        if(a>4&&b<4)
            return rand10();
        else
            return (a+b)%10+1;
    }
    */
}
