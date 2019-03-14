package test61;

/*
1. 对于序列化：使用前序遍历，递归的将二叉树的值转化为字符，并且在每次二叉树的结点
不为空时，在转化val所得的字符之后添加一个' ， '作为分割。对于空节点则以 '#' 代替。
 2. 对于反序列化：按照前序顺序，递归的使用字符串中的字符创建一个二叉树(特别注意：
在递归时，这样才能保证每次递归后指向字符串的指针会
随着递归的进行而移动！！！)
*/


class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    private int index =-1;
    String Serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        if(root== null){
            sb.append("#,");
            return  sb.toString();
        }
        //前序遍历
        sb.append(root.val+",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));
        return sb.toString();
    }
    //反序列化--前序遍历
    TreeNode Deserialize(String str) {
        index++;
        int len = str.length();
        if(index>= len){
            return null;
        }
        String[] strr = str.split(",");
        TreeNode node= null;
        if (!strr[index].equals("#")) {
            node = new TreeNode(Integer.valueOf(strr[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }
}



//public class Solution {
//    public int index = -1;
//
//    //序列化 --前序遍历
//    String Serialize(TreeNode root) {
//        StringBuffer sb = new StringBuffer();
//        if (root == null) {
//            sb.append("#,");
//            return sb.toString();
//        }
//        sb.append(root.val + ",");
//        sb.append(Serialize(root.left));
//        sb.append(Serialize(root.right));
//        return sb.toString();
//    }
//    //反序列化--使用前序递归遍历，进行反序列化--
//    TreeNode Deserialize(String str) {
//        index++;
//        int len = str.length();
//        if (index >= len) {
//            return null;
//        }
//        //
//        String[] strr = str.split(",");
//        TreeNode node = null;
//        if (!strr[index].equals("#")) {
//            node = new TreeNode(Integer.valueOf(strr[index]));
//            node.left = Deserialize(str);
//            node.right = Deserialize(str);
//        }
//        return node;
//    }
//}