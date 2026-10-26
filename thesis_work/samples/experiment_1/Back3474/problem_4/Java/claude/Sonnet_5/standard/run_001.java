import java.util.*;
import java.math.BigInteger;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode doubleIt(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode node = head;
        while (node != null) {
            sb.append(node.val);
            node = node.next;
        }
        BigInteger num = new BigInteger(sb.toString()).multiply(BigInteger.valueOf(2));
        String s = num.toString();
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (char c : s.toCharArray()) {
            cur.next = new ListNode(c - '0');
            cur = cur.next;
        }
        return dummy.next;
    }
}