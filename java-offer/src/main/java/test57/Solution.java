package test57;

/*
题目描述
给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
class TreeLinkNode {
    int val;
    TreeLinkNode left = null; //左子树
    TreeLinkNode right = null;//右子树
    TreeLinkNode next = null; //父节点

    TreeLinkNode(int val) {
        this.val = val;
    }
}

public class Solution {
    //GetNext--请返回某个节点按照中序遍历返回的下一个节点
    public TreeLinkNode GetNext(TreeLinkNode node) {
        //防御性编程,如果为空，直接返回
        if (node == null)
            return null;
        //如果节点的右子树不为空,直接返回下一个节点
        if (node.right != null) {
            node = node.right;
            while (node.left != null) { //有右子树的情况下，一直沿着最左边，查找到最左边的节点就是下一个节点
                node = node.left;
            }
            return node;
        }
        //没有右子树的情况，分为两种场景，左子树和右子树两种场景
        //左子树的下一个节点就是右子树，
        //右子树场景,沿父指针一直找到父节点最左边的节点
        while (node.next != null) {
            if (node.next.left == node) //判断是否是左子树，如果是左子树，直接返回父节点，
                return node.next;
            node = node.next; //一直向上查找父节点，一直找到当前节点是父节点的左子节点位置
        }
        return null; //若没有返回null
    }
}