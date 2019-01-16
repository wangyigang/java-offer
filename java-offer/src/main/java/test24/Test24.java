package test24;

import org.junit.Test;

import java.util.Deque;

/**
 * 题目描述
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class Test24 {
    @Test
    public void test(){
        //[4,8,6,12,16,14,10]
        int[] arr = {4,8,6,12,16,14,10};
        boolean b = VerifySquenceOfBST(arr);
        System.out.println(b);
    }
    //==>3
    public boolean VerifySquenceOfBST(int[] arr) {
        //防御性编程
        if(arr.length ==0){
            return false;
        }
        //使用递归方式
        return isCorrect(arr,0,arr.length-1);
    }

    private boolean isCorrect(int[] sequence, int start, int end) {
        //递归结束条件
        if(start >= end){
            return true;
        }
        //先进行查找左右子树交界处
        int i = start;
        for(; i<end ;i++){
            if(sequence[i]>sequence[end]){
                break;
            }
        }
        for(int j= i; j<end; j++){
            if(sequence[j]<sequence[end]){
                return false;
            }
        }

        return isCorrect(sequence, start, i-1)&& isCorrect(sequence, i, end-1);
    }

//    //==>2
//    public boolean VerifySquenceOfBST(int[] arr) {
//        if(arr.length ==0){
//            return false;
//        }
//        return isCorrect(arr, 0,arr.length-1);
//    }
//
//    private boolean isCorrect(int[] sequence, int start, int end) {
//        //递归结束条件
//        if(start>=end){
//            return true; //已经完全遍历
//        }
//        //找到左右子树分界线
//        int i = start;
//        for(; i<end; i++){
//            if(sequence[i]>sequence[end]){
//                break;
//            }
//        }
//        //此时假设左子树正确，遍历，右子树即可，判断右子树是否正确
//        for(int j= i; j<end; j++){
//            if(sequence[j]<sequence[end]){
//                return false;
//            }
//        }
//
//        return isCorrect(sequence, start,i-1 )&& isCorrect(sequence, i, end-1);
//    }


//    //==>1
//    public boolean VerifySquenceOfBST(int [] sequence) {
//        if(sequence.length ==0){
//            return  false;
//        }
//        return IsTreeBST(sequence, 0, sequence.length-1);
//    }
//
//    /**
//     * isTresBST:判断是否是后续树
//     * @param sequence
//     * @param start
//     * @param end
//     * @return
//     */
//    private boolean IsTreeBST(int[] sequence, int start, int end) {
//        if(start>= end){
//            return true;
//        }
//        //先从start开始，进行计算左右子树的起始位置
//        int i= start;
//        for(; i<end; i++){
//            if(sequence[i]>sequence[end]){
//                break;
//            }
//        }
//        //找到结束位置--假设左子树正确，就遍历右子树，判断右子树是否正确
//        for(int j=i; j<end; ++j){
//            //右子树的特点是每一个比根节点大，
//            if(sequence[j]<sequence[end]){
//                return false;
//            }
//        }
//        return IsTreeBST(sequence, start, i-1) && IsTreeBST(sequence, i, end-1);
//    }


//        public boolean VerifySquenceOfBST(int [] sequence) {
//            if(sequence.length == 0) return false; //如果长度为0，直接返回false;
//            return IsTreeBST(sequence, 0, sequence.length-1);//调用isTreeBST进行递归方式判断
//        }
//        //递归方式进行判断
//        public boolean IsTreeBST(int [] sequence,int start,int end ){
//            if(end <= start) return true; //终点小于等于起点，为true
//            int i = start;//从start位置开始，判断左子树，找到找到左子树的部分,,
//            for (; i < end; i++) {
//                if(sequence[i] > sequence[end]) break;
//            }
//            for (int j = i; j < end; j++) { //然后判断右子树：判断右子树是否比根节点小
//                if(sequence[j] < sequence[end]) return false;
//            }
//            //进行递归判断--左子树部分和右子树部分
//            return IsTreeBST(sequence, start, i-1) && IsTreeBST(sequence, i, end-1);
//        }
}
