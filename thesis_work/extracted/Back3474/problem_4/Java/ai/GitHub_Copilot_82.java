class Solution {
    public ListNode doubleIt(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        ListNode newHead = prev;
        ListNode newCurr = newHead;
        ListNode carry = null;
        while(newCurr != null) {
            int sum = newCurr.val * 2;
            if(carry != null) {
                sum += carry.val;
                carry = null;
            }
            if(sum > 9) {
                carry = new ListNode(sum / 10);
                sum = sum % 10;
            }
            newCurr.val = sum;
            newCurr = newCurr.next;
        }
        if(carry != null) {
            newCurr = newHead;
            while(newCurr.next != null) {
                newCurr = newCurr.next;
            }
            newCurr.next = carry;
        }
        return reverse(newHead);
    }
    
    public ListNode reverse(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}