class Solution {
    public ListNode doubleIt(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            curr.val *= 2;
            curr = curr.next;
        }
        return head;
        
    }
    
}