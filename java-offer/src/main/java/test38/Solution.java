package test38;

import org.junit.Test;

/*
题目描述
输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）
形成树的一条路径，最长路径的长度为树的深度。
 */
public class Solution {
    @Test
    public void test(){

    }

    //递归思想：判断左子树和右子树的深度，
    public int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;  //如果是空节点，深度为0
        }
        int leftDepth = TreeDepth(root.left);
        int rightDepth = TreeDepth(root.right);
        return leftDepth>rightDepth? leftDepth+1: rightDepth+1;
    }
}


class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}