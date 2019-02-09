package test35;


import org.junit.Test;

public class Solution {
    @Test
    public void test() {
        int[] arr = new int[]{1, 3, 4};
        int[] arr2 = new int[]{2, 3, 4};
        ListNode pHead1 = null;
        ListNode pHead2 = null;
        createListNodes(pHead1, arr);
        createListNodes(pHead2, arr2);
        ListNode listNode = FindFirstCommonNode(pHead1, pHead2);
        System.out.println(listNode.val);

    }
    //网上看到的新解法
    ListNode FindFirstCommonNode( ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while(p1!=p2){
            p1 = (p1==null ? pHead2 : p1.next);
            p2 = (p2==null ? pHead1 : p2.next);
        }
        return p1;
    }

    private void createListNodes(ListNode pHead, int[] arr) {
        for (int i : arr) {
            if (pHead == null) {
                pHead = new ListNode(i);
            } else { //不为空，有头结点
                //先找到尾节点
                ListNode node = pHead;
                while (pHead!= null){
                    node= pHead;
                    pHead= pHead.next;
                }
                ListNode pTail = new ListNode(i);
                node.next = pTail; //链向最后一个节点
            }
        }

    }

//    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
//        //异常情况
//       if(pHead1 == null || pHead2 == null){
//           return null;
//       }
//       int leftLength= 0;
//       ListNode left = pHead1;
//       while (left != null){
//           leftLength++;
//           left = left.next;
//       }
//       int rightLength=0;
//       ListNode right =pHead2;
//       while(right != null){
//           rightLength++;
//           right= right.next;
//       }
//
//
//    }
}


class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}