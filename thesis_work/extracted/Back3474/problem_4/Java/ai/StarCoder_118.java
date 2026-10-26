class Solution {
    public ListNode doubleIt(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode cur = dummy;
    while (cur.next != null && cur.next.next != null) {
        ListNode next = cur.next.next;
        cur.next.next =  next.next;
        next.next = cur.next;
        cur.next = next;
        cur = cur.next.next;
    }
    return dummy.next;
    }
}