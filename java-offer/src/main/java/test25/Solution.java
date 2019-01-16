package test25;


import org.junit.Test;

import java.util.ArrayList;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

/**
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 * 从根节点开始，
 */
//==>3
public class Solution{
    private ArrayList<ArrayList<Integer>>  allList = new ArrayList<>();
    private ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null){
            return  allList;
        }
        list.add(root.val);
        target-= root.val;

        if (target == 0 && root.left == null && root.right == null) {
            allList.add(new ArrayList<>(list));
        }
        FindPath(root.left ,target );
        FindPath(root.right, target);

        //去除尾元素
        list.remove(list.size()-1);

        return allList;
    }
}


////==>2
//public class Solution{
//    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
//    private ArrayList<Integer> list = new ArrayList<>();
//    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
//        //防御性编程
//        if(root== null){
//            return listAll;
//        }
//        //添加root节点
//        list.add(root.val);
//        target -= root.val;
//
//        //成功条件
//        if(target ==0 && root.left == null && root.right ==null){
//            listAll.add(new ArrayList<>(list));
//        }
//
//        FindPath(root.left, target);
//        FindPath(root.right, target);
//        //不成功，递归结束，将尾节点退出
//        list.remove(list.size()-1);
//
//        return listAll;
//    }
//}

//
////==>1
//public class Solution {
//    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
//    private ArrayList<Integer> list = new ArrayList<>();
//    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
//        if(root == null){
//            return listAll;
//        }
//        //先将root节点添加到List中国
//        list.add(root.val);
//        target -= root.val;
//        if(target ==0 && root.left == null && root.right == null){
//            listAll.add(new ArrayList<>(list)); //将list全部放入到listAll
//        }
//        FindPath(root.left, target);
//        FindPath(root.right, target);
//
//        //递归没有找到的话
//        list.remove(list.size()-1);
//
//        return  listAll;
//    }
//}



//public class Test25 {
//
//    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<>();
//    private ArrayList<Integer> list = new ArrayList<>();
//    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
//        //如果为空，直接返回
//        if(root == null){
//            return listAll;
//        }
//        //
//        //先将root节点添加到list中
//        list.add(root.val);
//        //减少target的值
//        target -= root.val;
//        //
//        if (target == 0 && root.left == null && root.right == null) {
//            //如果list加入到listAll中
//            listAll.add(new ArrayList<>(list));//将数据放入到arrayList中
//        }
//        //递归调用进入左子树
//        FindPath(root.left, target);
//        //递归调用进入右子树
//        FindPath(root.right, target);
//
//        list.remove(list.size()-1);//递归结束后，去除最后一个
//        return listAll;
//    }
//
//}
