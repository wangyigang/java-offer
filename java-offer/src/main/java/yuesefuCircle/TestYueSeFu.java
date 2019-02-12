package yuesefuCircle;

import org.junit.Test;

public class TestYueSeFu {
    @Test
    public void test(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next= node2;
        node2.next=node3;
        node3.next= node4;
        node4.next= node5;
        node5.next= node1;
        findLastNode(node1,3);
    }

    private void findLastNode(ListNode node1, int cnt) {
        ListNode cur = node1;
        ListNode pre = node1;
        while (true){

            if(cur.next == cur){
                System.out.println("最后剩余的是："+cur.val);
                break;
            }
            int k=cnt;
            while(--k!=0){
                pre = cur;
                cur = cur.next;
            }
            ListNode next = cur.next;
            System.out.println("当前被删除的值为:"+cur.val);
            pre.next = next;
            cur = cur.next;
        }
    }


}
class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
        this.next= null;
    }
}
