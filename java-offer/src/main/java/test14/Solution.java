package test14;


//import org.jruby.ast.ListNode;


class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution {
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode kToTail = head;
        while (k-- > 0) {
            if (cur != null) {
                cur = cur.next;
            } else {
                return null;
            }
        }
        while (cur != null) {
            cur = cur.next;
            kToTail = kToTail.next;
        }
        return kToTail;
    }
}