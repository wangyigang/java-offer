package test60;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


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
从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */

public class Solution {
    @Test
    public void test(){
//        [[8],[6,10],[5,7,9,11]]
//        [[8],[6,10],[5,7,9,11]]
//
//        你的输出为:
//        [[6,10],[5,7,9,11]]
    }

    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if(pRoot == null){
            return ret;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        queue.add(null); //先添加分隔符
        queue.add(pRoot);

        while(queue.size()!= 1){
            TreeNode node = queue.removeFirst();
            if(node ==null){
                Iterator<TreeNode> iter = queue.iterator();
                while(iter.hasNext()){
                    list.add(iter.next().val);
                }
                ret.add(new ArrayList<>(list));
                list.clear();
                queue.add(null);
                continue;
            }

            if(node.left!= null){
                queue.add(node.left);
            }
            if(node.right!= null){
                queue.add(node.right);
            }
        }
        return ret;
    }

}

