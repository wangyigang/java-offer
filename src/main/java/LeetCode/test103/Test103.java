package LeetCode.test103;

import org.junit.Test;

import java.util.*;

public class Test103 {
    @Test
    public void test(){
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        root.left = node1;
        TreeNode node2= new TreeNode(20);
        root.right=node2;
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        node2.left=node3;
        node2.right=node4;
        List<List<Integer>> list = zigzagLevelOrder(root);
        for (List<Integer> integerList : list) {
            for (Integer integer : integerList) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        //声明一个队列，存放节点
        Deque<TreeNode> deque = new LinkedList<>();
        //声明一个list，存放每层的值，然后将值放入result中
        LinkedList<Integer> list = new LinkedList<>(); //不能使用arraylist,arraylist不能头插
        //将头结点元素放入deque
        deque.offer(root);
        //记录个数和boolean
        int size = deque.size();
        boolean flag = true;
        while (!deque.isEmpty()) {
            if(size==0){
                result.add(list);
                list = new LinkedList<>();//重新生成一个容器存放元素
                size=deque.size();
                flag = !flag;
            }
            //取出头结点
            TreeNode node = deque.poll();
            --size;
            if(node.left!=null){
                deque.offer(node.left);
            }
            if(node.right!=null){
                deque.offer(node.right);
            }
            //存放到list中
            if(flag){
                list.add(node.val);
            }else{
               //list.add(0, node.val);
                list.addFirst(node.val);
            }
        }
        //将最后一次结果存放在result中
        if (!list.isEmpty()) {
            result.add(list);
        }
        return result;
    }



//    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
//        List<List<Integer>> result = new ArrayList<>();
//        if(root == null){
//            return result;
//        }
//        //单层list--deque
//        Deque<TreeNode> singleLevel = new LinkedList<>();
//        //将根节点放入队列中
//        singleLevel.push(root);
//        int size = singleLevel.size();//记录当前节点个数
//        LinkedList<Integer> list = new LinkedList<>();
//        boolean flag = true;
//        while (!singleLevel.isEmpty()) {//当前不为空
//            //当前层结束条件
//            if(size ==0){
//                result.add(list);//将当前层数值放入result中
//                list = new LinkedList<>();//重新生成一个新的list
//                size = singleLevel.size();
//                //将boolean变量置反
//                flag = !flag;
//            }
//            //取出当前队列首个节点
//            TreeNode node = singleLevel.poll();
//            --size; // 个数减减
//            //按照层次遍历进行放入队列中
//            if(node.left!=null){
//                singleLevel.offer(node.left);
//            }
//            if(node.right!=null){
//                //
//                singleLevel.offer(node.right);
//            }
//            //通过判断flag将元素放入list中
//            if(flag){//从左向右
//                list.add(node.val);
//            }else{//从右向左
//                list.addFirst(node.val);
//            }
//        }
//        //最后一次的元素没有放入到result中
//        if (!list.isEmpty()) {
//            result.add(list);
//        }
//        return result;
//    }

    //计算个数，通过boolean变量进行反转方式
//   public List<List<Integer>> zigzagLevelOrder(TreeNode root){
//       List<List<Integer>> result = new ArrayList<>();
//       if (root == null) {
//           return result;
//       }
//       //双端队列
//       Queue<TreeNode> queue = new LinkedList<>();
//       queue.add(root); //先把根节点放进去
//       int size = queue.size(); // 记录每层的结点个数
//       boolean flag = true;
//
//       LinkedList<Integer> singleLevel = new LinkedList<>();
//       while (!queue.isEmpty()) {
//           if (size == 0) {// 一层记录结束
//               result.add(singleLevel); //单层结束，将结果放入
//               size = queue.size(); //重新计算queue的个数
//               singleLevel = new LinkedList<>();//重新new一个singleLevel
//               flag = !flag; //boolean反向
//           }
//           TreeNode tempNode = queue.poll();
//           if (flag) {//flag控制输入的路径
//               singleLevel.add(tempNode.val);//从左到右
//           } else {
//               singleLevel.addFirst(tempNode.val);//从右向左
//           }
//           --size; //个数减减
//
//           if (tempNode.left != null) {
//               queue.offer(tempNode.left); //层次遍历，放入
//           }
//           if (tempNode.right != null) {
//               queue.offer(tempNode.right);//层次遍历，放入
//           }
//       }
//       result.add(singleLevel); //放入一层的结果
//       return result;
//   }



// {public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//        if(root == null){
//            return null;
//        }
//        Deque<TreeNode> deque = new ArrayDeque<>();
//        deque.push(root);
//        boolean bReverse = false;
//        while (!deque.isEmpty()) {
//            //先获取第一个
//            TreeNode node = deque.poll();
//            //按照层级关系进行放入到队列中,类似广度优先
//            if (node.left!= null) {
//                deque.push(node.left);
//            }
//            if(node.right!=null){
//                deque.push(node.right);
//            }
//        }
//    }

}
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}