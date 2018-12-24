package LeetCode.test515;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test515 {
    @Test
    public void test() {
        int[] arr = new int[]{1, 3, 2, 5, 3, -1, 9};
        TreeNode treeNode = creatTree(arr, 0, -1);
        List<Integer> list = largestValues(treeNode);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);

        while (!que.isEmpty()) {
            int max = que.peek().val;
            int size = que.size();
            while (size > 0) {
                if (max < que.peek().val) {
                    max = que.peek().val;
                }
                //先进行层次遍历，将根节点的
                if (que.peek().left != null) {
                    que.add(que.peek().left);
                }
                if (que.peek().right != null) {
                    que.add(que.peek().right);
                }
                //
                que.poll();
                --size;
            }
            list.add(max);

        }
        return list;
    }


//    public List<Integer> largestValues(TreeNode root) {
//        List<Integer> list=new ArrayList();
//        if(root==null)return list;
//        Queue<TreeNode> q=new LinkedList();
//        q.add(root);
//        int max;
//        //队列不为空
//        while(!q.isEmpty()){
//            int size=q.size();
//            max=q.peek().val;
//            while(size>0){
//                TreeNode tem=q.poll(); //查看队列中第一个,第一次是根节点
//                if(tem.val>max)
//                    max=tem.val;
//                if(tem.left!=null) //按照层次将元素放进队列中
//                    q.add(tem.left);
//                if(tem.right!=null)
//                    q.add(tem.right);
//                size--;
//            }
//            list.add(max);
//        }
//        return list;
//    }


//    public List<Integer> largestValues(TreeNode root) {
//        List<Integer> result = new ArrayList<>();
//        //中序遍历，进行查找
//        Queue<TreeNode> q = new Queue<>();
//        if (root == null)
//        {
//            return result;
//        }
//        q.add( root);
//        while (!q.isEmpty())
//        {
//            if (q.peek().left!= null )
//            {
//                q.add(q.peek().left);
//
//            }
//            if (q.peek().right != null )
//            {
//                q.add(q.peek().right);
//
//            }
//            System.out.println(q.peek().val);
//            //去除第一个节点
//            q.poll();
//        }
//        return result;
//    }


    TreeNode creatTree(int[] arr, int index, int invalid) {
        TreeNode root = null;
        if (index < arr.length && arr[index] != invalid) {
            root = new TreeNode(arr[index]);   //create node
            root.left = creatTree(arr, ++index, invalid);
            root.right = creatTree(arr, ++index, invalid);
        }
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}