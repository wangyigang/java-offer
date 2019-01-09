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
    //递归方式进行倒置链表
    public ListNode reverseList(ListNode head){
        //防御型编程  递归结束条件
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = reverseList(next);

        head.next = null; // 先将原来的连接关系置空
        next.next = head; // 递归每次的head都是next的前一个

        return newHead;
    }





//    //递归方式进行倒置链表
//    public ListNode reverseList(ListNode head){
//        //防御型编程
//        if(head == null || head.next==null){ //或递归结束条件
//            return head;
//        }
//        ListNode n = head.next; //n是下一个节点
//        ListNode node = reverseList(n);//node是最后一个节点
//        //最后一个节点
//        head.next = null;//head为2
//        n.next = head; // head在递归中时前一个
//        return node;
//    }


    /*
    递归方式进行倒置链表
     */
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

