package test03;

import java.util.ArrayList;
import java.util.Stack;

public class Test03 {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> s = new Stack<>();

        while (listNode != null) {
            s.push(listNode.val);
            listNode = listNode.next;
        }

        ArrayList<Integer> ret = new ArrayList<>();
        while (!s.empty()) {
            ret.add(s.peek());
            s.pop();
        }
        return ret;
    }
}


class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
 

