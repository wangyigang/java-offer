package LeetCode.test82;

import org.junit.Test;

/*
给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
示例 1:
输入: 1->2->3->3->4->4->5
        1->2->3->4->4->5
        1->2->4->4->5
        1->2->4->5

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
    //方式一：链表节点的方式进行删除
    public ListNode deleteDuplicates(ListNode head){
        //假借一个假的头节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = head;

        boolean bIsDel = false;
        while (cur!= null && cur.next != null){
            if(cur.val == cur.next.val){
                //值相同时，将后一个删除掉
                ListNode next = cur.next.next;
                cur.next = next;
                //同时将bIsDel置为true，要在后面进行删除
                bIsDel = true;
            }else{
                if(bIsDel){
                    //需要将第一个删除掉
                    pre.next = cur.next;
                    cur = cur.next;
                    bIsDel = false;
                }else{
                    pre = cur;
                    cur = cur.next;
                }
            }
        }
        if(bIsDel){
            pre.next= cur.next;
//            cur = cur.next;
        }
        return dummyHead.next;
    }



//    public ListNode deleteDuplicates(ListNode head){
//        ListNode dummyHead = new ListNode(0); //自己造一个假的节点
//        dummyHead.next = head;
//        ListNode pre = dummyHead;
//        ListNode cur = head;
//        boolean curDel = false;
//        while (cur != null && cur.next != null){
//            if(cur.val == cur.next.val){//两个节点都要删除
//                ListNode next = cur.next.next;
//                cur.next = next;
//                curDel = true;
//            }else{//
//                if(curDel){//当前节点也需要被删除，跳过
//                    pre.next = cur.next; //更新pre的下一个指向
//                    cur = pre.next; //更新cur为cur的下一个值，将第一个相同值给忽略掉
//                    curDel = false;//将boolean变量再置为false
//                }else{ //
//                    pre=cur;
//                    cur=cur.next;
//                }
//            }
//        }
//        if(curDel){ //
//            pre.next=cur.next;
//            cur = cur.next;
//            //curDel = false;
//        }
//        return  dummyHead.next;
//    }

    /*
    指针方式：
        感觉需要三个指针，才能解决问题
        新建里一个虚假的头结点dummyHead
        dummyHead-> head
        pre = dummyHead
        cur=head;
     */
//    public ListNode deleteDuplicates(ListNode head) {
//            ListNode dummyHead = new ListNode(0);
//            dummyHead.next = head;
//            ListNode pre = dummyHead;
//            ListNode cur = head;
//            // 标识当前节点是否需要删除
//            boolean curIsDelete = false;
//            while (cur != null && cur.next != null){
//                if (cur.val == cur.next.val) {
//                    cur.next = cur.next.next;
//                    // 出现重复节点, 将当前cur标记为需要删除
//                    curIsDelete = true;
//                } else {
//                    if (curIsDelete) {
//                        pre.next = cur.next;
//                        cur = pre.next;
//                        // 新的cur节点,所以需要重置curIsDelete
//                        curIsDelete = false;
//                    } else {
//                        pre = cur;
//                        cur = cur.next;
//                    }
//                }
//            }
//            //将最后一次节点的删除掉
//            if (curIsDelete) {
//                pre.next = cur.next;
//                cur = pre.next;
//                // 新的cur节点,所以需要重置curIsDelete
//                curIsDelete = false;
//            }
//            return dummyHead.next;
//
//    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}