package test16;

import org.junit.Test;

/*
题目描述
输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class Solution {
    @Test
    public void test(){
        ListNode list1= new ListNode(1);
        ListNode list2= new ListNode(3);
        ListNode list3= new ListNode(5);
        ListNode list4= new ListNode(7);
        list1.next=list2;
        list2.next=list3;
        list3.next=list4;

        ListNode rigth1= new ListNode(2);
        ListNode rigth2= new ListNode(4);
        ListNode rigth3= new ListNode(6);
        ListNode rigth4= new ListNode(8);
        rigth1.next=rigth2;
        rigth2.next=rigth3;
        rigth3.next=rigth4;
        ListNode merge = Merge(list1, rigth1);
        while (merge!=null){
            System.out.println(merge.val);
            merge=merge.next;
        }
    }
    public ListNode Merge(ListNode list1, ListNode list2) {
        //防御性编程
        if(list1==null){
            return list2;
        }
        if(list2==null){
            return list1;
        }
        //先找到一个头结点，从list1 和list2中
        ListNode newHead = null;
        if(list1.val <list2.val){
            newHead = list1;
            list1=list1.next;
        }else{
            newHead = list2;
            list2=list2.next;
        }
        ListNode cur = newHead;
        while(list1!=null && list2!=null){
            if(list1.val < list2.val){
                cur.next= list1;
                list1=list1.next;
            }else{
                cur.next=list2;
                list2=list2.next;
            }
            cur=cur.next;
        }
        //list1或者list2 当长度不相同的情况，会有一个还有剩余的情况
        if(list1!=null){
            cur.next=list1;
        }
        if(list2!=null){
            cur.next=list2;
        }

        //进行比较
        return newHead;
    }
}