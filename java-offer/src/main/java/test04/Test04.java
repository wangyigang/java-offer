package test04;

public class Test04 {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length == 0 || in.length == 0) {
            return null;
        }
        return buildTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode buildTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        TreeNode root = new TreeNode(pre[preStart]);
        int rootIn = 0;//前序遍历特点，所以第一个一定为0;然后找根节点在中序遍历中的位置index
        for (; rootIn < inEnd; ++rootIn) {
            if (in[rootIn] == root.val) {
                break;//直接break即可，此时rootIn中存储了中序数组中的根节点的位置
            }
        }
        //算出中序数组中左子树的长度
        int leftLength = rootIn - inStart; //不需要减一
        //算出中序数组中右子树的长度
        int rightLefth = inEnd - rootIn;
        if (leftLength > 0) {//递归
            //递归中，preStart+1属于第二次起始位置，到preStart+leftLength,  核心思想是把属于左子树的范围给他们
            root.left = buildTree(pre, preStart + 1, preStart + leftLength, in, inStart, rootIn - 1);
        }
        if (rightLefth > 0) {//递归
            //核心思想是把属于右子树的范围给右子树进行递归
            root.right = buildTree(pre, preStart + leftLength + 1, preEnd, in, rootIn + 1, inEnd);
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

