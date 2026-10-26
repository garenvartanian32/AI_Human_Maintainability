class Solution {
    public ListNode doubleIt(ListNode head) {
        ListNode curr = head;
        int currVal = 0;
        while (curr!= null) {
            currVal += curr.val;
            curr.val = currVal;
            curr = curr.next;
        }
        curr = head;
        while (curr!= null) {
            curr.val = curr.val * 2;
            curr = curr.next;
        }
        return head;
    }
}