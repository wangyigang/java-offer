package LeetCode.test82;

import org.junit.Test;

/*
给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
示例 1:
输入: 1->2->3->3->4->4->5
输出: 1->2->5
示例 2:

输入: 1->1->1->2->3
输出: 2->3
 */
public class Test82 {
    @Test
    public void test() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        ListNode result = deleteDuplicates(head);
        while (result!=null){
            System.out.println(result.val);
            result = result.next;
        }
    }
    /*
    指针方式：

     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode fir = head;
        ListNode sec= null;
        while (fir!=null){
            if(sec!=null&&fir.val == sec.val){
                ListNode next = fir.next;
                sec.next = next;
                fir.next=null;
                fir = next;
                sec=null;
            }else{
                sec = fir;
                fir=fir.next;
            }
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next=null;
    }
}