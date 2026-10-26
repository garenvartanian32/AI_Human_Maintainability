class Solution {
    public ListNode doubleIt(ListNode head) {
        // Convert linked list to number
        long num = 0;
        while (head != null) {
            num = num * 10 + head.val;
            head = head.next;
        }

        // Double the number
        num *= 2;

        // Convert number back to linked list
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        do {
            curr.next = new ListNode((int)(num % 10));
            num /= 10;
            curr = curr.next;
        } while (num > 0);

        // Reverse the linked list and return
        return reverse(dummy.next);
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}