package test56;

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
        在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，
        返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
        */
public class Solution {
    @Test
    public void test(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

        ListNode result = deleteDuplication(l1);
        while (result!=null){
            System.out.print(result.val+" ");
            result = result.next;
        }
    }
    //=>3
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode cur = pHead;
        ListNode prev = new ListNode(-1); //假头节点
        prev.next = cur;
        ListNode result = prev;
        while(cur!= null){
            //相同
            if(cur.next!= null && cur.val == cur.next.val){
                //进行查找
                while(cur.next!= null && cur.val == cur.next.val){
                    cur = cur.next;
                }
                //找到后
                cur=cur.next;
                prev.next=cur;
            }else{//不同
                cur=cur.next;
                prev=prev.next;
            }
        }
        return result.next;
    }


//    public ListNode deleteDuplication(ListNode pHead) {
//        ListNode cur = pHead;
//        ListNode prev = new ListNode(-1);
//        prev.next = cur;
//        ListNode result = prev;
//        while(cur!= null){
//            if(cur.next!= null && cur.next.val == cur.val){
//                //while循环找到不相同的节点
//                while(cur.next!= null && cur.val == cur.next.val){
//                    cur = cur.next;
//                }
//                //不同
//                cur=cur.next;
//                prev.next = cur;
//            }else{
//                prev = prev.next;
//                cur = cur.next;
//            }
//        }
//        return result.next;
//    }

    /*
        场景一： 头结点即为重复节点
        场景二： 头结点不是重复节点，重复节点在后面

        index是前一个
        temp是当前节点
//     */
    public ListNode deleteDuplication2(ListNode pHead) {

        ListNode cur=pHead;
        ListNode prev=new ListNode(-1); //借用一个假头
        prev.next=pHead;
        ListNode result=prev;  //result指向index 假头
        while(cur!=null){
            if(cur.next!=null&&cur.next.val==cur.val){
                while(cur.next!=null&&cur.next.val==cur.val){
                    cur=cur.next;
                }
                cur=cur.next; //先指向下一个temp下一个节点
                prev.next=cur; //index假头.next 指向temp
            }
            else{
                prev=prev.next;
                cur=cur.next;
                //没有改变头结点，头结点还是在index.next指向处
            }
        }
        return result.next;
    }
}
