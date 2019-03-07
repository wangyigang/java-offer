package test59;

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
请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
使用双端队列方式

 */

public class Solution {
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if(pRoot== null){
            return ret;
        }
        //创建一个队列和一个arraylist
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>(); //创建双端队列，进行存放数据，好处，可以从左边或者右边都可以访问

        queue.addLast(null); //将null作为分层器
        //先将根节点添加进去
        queue.addLast(pRoot);

        boolean leftToright = true; //标志方向
        while(queue.size() !=1){
            TreeNode node = queue.removeFirst();
            if(node == null){
                //根据标志位判断迭代器方向
                Iterator<TreeNode> iterator = null;
                if(leftToright){
                    iterator = queue.iterator();
                }else{
                    iterator = queue.descendingIterator();// 反向迭代器
                }
                //标志位置反
                leftToright=!leftToright;

                //利用迭代器将数据放入容器中
                while(iterator.hasNext()){
                    list.add(iterator.next().val);
                }
                //迭代完成后，将所有数据放入到ret中
                ret.add(new ArrayList<>(list));
                list.clear(); //清除原来的数据
                //添加分层符
                queue.add(null);
                continue;

            }
            if(node.left != null){
                queue.addLast(node.left);
            }
            if(node.right != null){
                queue.addLast(node.right);
            }

        }
        return ret;
    }



//    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
//        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
//        if (pRoot == null) {
//            return ret;
//        }
//
//        ArrayList<Integer> list = new ArrayList<>();
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.addLast(null); //分隔符
//        queue.addLast(pRoot);
//        boolean leftToright = true;
//        while (queue.size() != 1){
//            TreeNode node = queue.removeFirst(); //removeFirst
//            if(node == null){
//                //使用迭代器方式
//                Iterator<TreeNode> iter = null;
//                if(leftToright){
//                    iter = queue.iterator();
//                }else{
//                    //反向迭代器
//                    iter = queue.descendingIterator();
//                }
//                //直接取反
//                leftToright=!leftToright;
//                while(iter.hasNext()){
//                    TreeNode tmp = iter.next();
//                    list.add(tmp.val);
//                }
//                //迭代器放完以后，将结果集放入result中
//                ret.add(new ArrayList<>(list));
//                //将list中数据清空
//                list.clear();
//                //在添加层分隔符
//                queue.addLast(null);
//                continue;
//            }
//
//            //判断左子节点是否为空
//            if(node.left != null){
//                queue.addLast(node.left);
//            }
//            if(node.right!= null){
//                queue.addLast(node.right);
//            }
//        }
//
//        return ret;
//    }

//    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
//        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
//        if (pRoot == null) {
//            return ret;
//        }
//
//        ArrayList<Integer> list = new ArrayList<>();
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.addLast(null);//层分隔符
//        queue.addLast(pRoot);
//        boolean leftToRight = true;
//
//        while (queue.size() != 1) {
//            TreeNode node = queue.removeFirst();
//            if (node == null) {//到达层分隔符
//                Iterator<TreeNode> iter = null;
//                if (leftToRight) {
//                    iter = queue.iterator();//从前往后遍历
//                } else {
//                    iter = queue.descendingIterator();//从后往前遍历
//                }
//                leftToRight = !leftToRight;
//                while (iter.hasNext()) {
//                    TreeNode temp = iter.next();
//                    list.add(temp.val);
//                }
//                ret.add(new ArrayList<>(list));
//                list.clear();
//                queue.addLast(null);//添加层分隔符
//                continue;//一定要continue
//            }
//            if (node.left != null) {
//                queue.addLast(node.left);
//            }
//            if (node.right != null) {
//                queue.addLast(node.right);
//            }
//        }
//        return ret;
//    }

}