package test62;

import org.junit.Test;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}
/*
题目描述
给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 */

//前序遍历可以解决左中右
public class Solution {
    private int index = 0;

    //中序遍历
    TreeNode KthNode(TreeNode root, int k) {
        //首先判断root节点是否为null，也是递归条件
        if (root != null) {
            //中序遍历
            TreeNode treeNode = KthNode(root.left, k);
            if (treeNode != null) {
                return treeNode;
            }
            index++;
            if (index == k) {
                return root;
            }
            treeNode = KthNode(root.right, k);
            if (treeNode != null) {
                return treeNode;
            }
        }
        return null;
    }


    //前序遍历时根左左右，，按照大小排序的是左根右，所以是中序遍历
//    int index = 0; //计数器
//    TreeNode KthNode(TreeNode root, int k)
//    {
//        if(root != null){ //中序遍历寻找第k个
//            TreeNode node = KthNode(root.left,k);
//            if(node != null)  //node是递归的返回值，如果不为空，表示已经返回正确index ==k 的结果，所以正确返回Node节点即可
//                return node;
//            index ++;
//            if(index == k)
//                return root;
//            node = KthNode(root.right,k);
//            if(node != null)
//                return node;
//        }
//        return null;
//    }
    @Test
    public void test() {
        //{8,6,10,5,7,9,11},1
        TreeNode root = new TreeNode(8);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);

        //拼接树
        root.left = node6;
        root.right = node10;

        node6.left = node5;
        node6.right = node7;

        node10.left = node9;
        node10.right = node11;

        TreeNode treeNode = KthNode(root, 1);
        System.out.println(treeNode.val);
    }
}