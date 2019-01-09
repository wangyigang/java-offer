package test15;

//import org.jruby.ast.ListNode;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution {
    public ListNode ReverseList(ListNode head) {
        ListNode reverseHead = null;
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (next == null) {
                reverseHead = cur;
            }
            //改变连接方向
            cur.next = prev;

            //移动元素
            prev = cur;
            cur = next;

        }
        return reverseHead;
    }
}