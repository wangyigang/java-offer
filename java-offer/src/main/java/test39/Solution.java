package test39;


/*
题目描述
输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        //防御性编程
        if(root== null){
            return true;
        }
        int leftDepth= TreeDepth(root.left);
        int rightDepth = TreeDepth(root.right);
        int diff = leftDepth-rightDepth;
        if(diff>1 || diff <-1)
            return false;

        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    private int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;  //如果是空节点，深度为0
        }
        int leftDepth = TreeDepth(root.left);
        int rightDepth = TreeDepth(root.right);
        return leftDepth>rightDepth? leftDepth+1: rightDepth+1;
    }
}
/*

 */
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}
