package test55;


import org.junit.Test;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

/*
题目描述
给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class Solution {
    @Test
    public void test(){
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next=listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode3;
        System.out.println(EntryNodeOfLoop(listNode1).val);
    }

    /*
        注： 有两点： 一：第一步查找是否有环时，使用快慢指针方式，快指针走两步，慢指针走一步
                     二：当确定有环以后，满指针重新指向头结点，然后两个一起走，同时每次走一步，这样可以找到入口节点
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        //防御性编程
        if(pHead == null || pHead.next == null || pHead.next.next==null){
            return null;
        }
        ListNode slow = pHead.next;
        ListNode fast = slow.next;
        while(slow != fast){
            if(slow.next != null && slow.next.next !=null){
                slow = slow.next; //慢指针走一步
                fast= fast.next.next; //快指针走两步
            }else{
                return null;
            }
        }
        //此时循环出来以后，就是有环链表
        slow = pHead;//重新指向头结点
        while(slow!= fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;

    }
}