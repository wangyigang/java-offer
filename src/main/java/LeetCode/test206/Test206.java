package LeetCode.test206;

import org.junit.Test;

import java.sql.Array;

/*
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class Test206 {
    @Test
    public void test(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
//        ListNode l4 = new ListNode(4);
//        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = null;
//        l4.next = l5;
//        l5.next = null;

        ListNode listNode = reverseList(l1);
        while (listNode!= null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    /*
    递归方式进行倒置链表
     */
    public ListNode reverseList(ListNode head) {
        //递归结束条件
        if(head.next == null){
            return head;//返回最后一个值
        }
        //
        ListNode next = head.next;
        //返回结果是后一个值
        ListNode node = reverseList(next);

        //递归结束的时候最后一个值是返回的第一个
        head.next = null;
        next.next = head;

        return node;

    }



//    public ListNode reverseList(ListNode head) {
//        //递归方式
//        if(head.next == null){
//            return head;
//        }
//        ListNode next = head.next;
//        ListNode node = reverseList(next);
//
//        head.next = null;//最后一个节点
//        next.next = head;
//
//        return  node;
//    }

}


 class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }

