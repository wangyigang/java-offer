package test23;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

}

/**
 * 层次遍历二叉树--从上到下，同层从左到右
 */
public class Solution {
    //==>3
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list  = new ArrayList<>();
        if(root == null){
           return list;
        }
        //队列方式
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while(!deque.isEmpty()){
            TreeNode node = deque.pop();
            list.add(node.val);

            if(node.left!=null){
                deque.addLast(node.left);
            }
            if(node.right!=null){
                deque.addLast(node.right);
            }
        }

        return list;
    }

//    //==>2
//    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
//        ArrayList<Integer> list = new ArrayList<>();
//        if(root == null){
//            return list;//如果为空，返回空集合
//        }
//        Deque<TreeNode> deque = new LinkedList<>();
//        deque.addLast(root);
//        while(!deque.isEmpty()){//如果不为空
//            TreeNode node = deque.pop();// 队列头部数据出队列
//            list.add(node.val);//添加到集合中
//
//            if(node.left!=null){//层次遍历，添加左子树
//                deque.addLast(node.left);
//            }
//            if(node.right !=null){//层次遍历，添加右子树
//                deque.addLast(node.right);
//            }
//        }
//        return list;
//    }



    //==>1
//    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
//        if (root == null) {
//            return new ArrayList<>(); //如果为空的时候，返回空结构--本题中的测试数据的要求
//        }
//
//        ArrayList<Integer> list = new ArrayList<>();
//        Deque<TreeNode> deque = new LinkedList<>();
//        deque.add(root);
//        while (!deque.isEmpty()) { //不为空的时候
//            //通过队列方式进行层次遍历
//            TreeNode node = deque.pop();
//            list.add(node.val);
//            if (node.left != null) {
//                deque.addLast(node.left);
//            }
//            if (node.right != null) {
//                deque.addLast(node.right);
//            }
//        }
//
//        return list;
//    }
}