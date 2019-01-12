package test17;


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
输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class Solution {

    @Test
    public void test() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        //建立树
        node2.left = node1;
        node2.right = node3;
        node4.left = node2;
        node4.right = node5;
        node5.right = node6;
        boolean b = HasSubtree(node4, node2);
        System.out.println(b);

    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        //防御性编程，第一次进入判断
        if(root2 == null){
            return false;
        }
        if(root1 == null && root2 != null){
            return false;
        }
        boolean flag = false;
        if(root1.val == root2.val){
            flag = isHasSubTree(root1, root2);
        }
        if (!flag){
            flag = HasSubtree(root1.left, root2);
            if(!flag){
                flag = HasSubtree(root1.right, root2);
            }
        }
        return flag;
    }

    private boolean isHasSubTree(TreeNode root1, TreeNode root2) {
        if(root2 == null){
            return true;
        }
        if(root1 == null && root2 != null){//说明root2还有数据没有验证完，此时不是
            return false;
        }
        if(root1.val == root2.val){
            return isHasSubTree(root1.left, root2.left) && isHasSubTree(root1.right, root2.right);
        }else{
            return false; // 如果不相同，直接返回false; --因为值已经不相同了，直接返回false
        }
    }


//    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
//        //防御性编程
//        if(root2 == null){
//            return false;
//        }
//        if(root1 == null && root2!=null){
//            return false; //直接返回
//        }
//        boolean flag = false;
//        if(root1.val == root2.val){ //值相同，调用判断子树算法
//             flag = isHasSubTree(root1, root2);
//        }
//        if(!flag){ // 不正确，继续查找左子树
//            flag = HasSubtree(root1.left, root2);
//            if(!flag){
//                flag = HasSubtree(root1.right, root2);
//            }
//        }
//        return flag;
//    }
//
//    private boolean isHasSubTree(TreeNode root1, TreeNode root2) {
//        if(root2 == null){ //此处一定是递归进入isHasSubTree时，进行判断，如果root2为空，则表明已经处于查找结束的状态
//            return true; //此时，也只能用root2进行递归结束条件，root1可能很大，不会查询完
//        }
//        if(root1 == null && root2!=null){
//            return false;
//        }
//        if(root1.val == root2.val){
//            return isHasSubTree(root1.left, root2.left)&& isHasSubTree(root1.right, root2.right);
//        }else{
//            return false;
//        }
//    }


    /**
     * 递归方式进行处理
     * @param root1
     * @param root2
     * @return
     */
//    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
//        if (root2 == null) return false; //如果root2为空，直接返回false;
//        if (root1 == null && root2 != null) { //或者root1为空，root2不为空，也返回false
//            return false;
//        }
//        //
//        boolean flag = false;
//        if (root1.val == root2.val) { //如果root1和root2的值相同，进行判断子节点是否相同
//            flag = isSubTree(root1, root2);//isSubTree
//        }
//        if (!flag) { //如果不相同，递归查找左子树
//            flag = HasSubtree(root1.left, root2);
//            if (!flag) { //不相同，继续递归查找右子树
//                flag = HasSubtree(root1.right, root2);
//            }
//        }
//        return flag;
//    }
//
//    //判断子树
//    private boolean isSubTree(TreeNode root1, TreeNode root2) {
//        if (root2 == null) return true; //如果root2为空，直接返回true
//        if (root1 == null && root2 != null) return false; // root1为空，root2不为空，返回false
//        if (root1.val == root2.val) {//如果两个值相同，进行递归查找左子树和递归查找右子树
//            return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
//        } else {
//            return false; //不相同，直接返回false;
//        }
//    }


//    //判断 是否是子树
//    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
//        if(root1==null || root2 == null){
//            return false;
//        }
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root1);
//        //使用广度优先算法策略，先找到两个根相同的节点，如果找到后，继续使用广度优先算法，判断是否继续相同
//        while(!queue.isEmpty()){
//            //如果根节点相同，继续判断
//            TreeNode node = queue.remove();
//            if(node.val == root2.val){
//                if(bfs(node,root2)){
//                    return true;
//                }
//            }
//
//            //广度优先方式继续放入队列中
//            if(node.left !=null){
//                queue.add(node.left);
//            }
//
//            if(node.right !=null){
//                queue.add(node.right);
//            }
//
//        }
//        return false;
//
//    }
//    public boolean bfs(TreeNode left, TreeNode right){
//        Queue<TreeNode> que1 = new LinkedList<>();
//        Queue<TreeNode> que2 = new LinkedList<>();
//        que1.add(left);
//        que2.add(right);
//
//        while((!que1.isEmpty()) && (!que2.isEmpty())){
//            TreeNode node1 = que1.remove();
//            TreeNode node2 = que2.remove();
//
//            if(node1.val != node2.val){
//                return  false;
//            }
//            if(node1.left !=null){
//                if(node2.left==null){
//                    return false;
//                }
//                que1.add(node1.left);
//                que2.add(node2.left);
//            }
//
//            if(node1.right !=null){
//                if(node1.right ==null){
//                    return false;
//                }
//                que1.add(node1.right);
//                que2.add(node2.right);
//            }
//
//        }
//        return true;
//    }

//    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
//        if(root1 == null || root2 == null) {
//            return false;
//        }
//
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root1);
//
//        while(!queue.isEmpty()) {
//            TreeNode node = queue.remove();
//            if(node.val == root2.val) {
//                if(bfs(root2,node)) {
//                    return true;
//                }
//            }
//
//            if(node.left != null) {
//                queue.add(node.left);
//            }
//
//            if(node.right != null) {
//                queue.add(node.right);//            }
//        }
//
//        return false;
//    }
//
//    public boolean bfs(TreeNode root1,TreeNode root2) {
//        Queue<TreeNode> queue1 = new LinkedList<>();
//        Queue<TreeNode> queue2 = new LinkedList<>();
//        queue1.add(root1);
//        queue2.add(root2);
//
//        while(!queue1.isEmpty() && !queue2.isEmpty()) {
//            TreeNode node1 = queue1.remove();
//            TreeNode node2 = queue2.remove();
//            if(node1.val != node2.val) {
//                return false;
//            }
//
//            if(node1.left != null) {
//                if(node2.left == null) {
//                    return false;
//                }
//                queue1.add(node1.left);
//                queue2.add(node2.left);
//            }
//
//            if(node1.right != null) {
//                if(node2.right == null) {
//                    return false;
//                }
//                queue1.add(node1.right);
//                queue2.add(node2.right);
//            }
//        }
//
//        return true;
//    }


}