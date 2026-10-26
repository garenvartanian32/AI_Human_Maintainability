public class Solution {
    public ListNode doubleIt(ListNode head) {
        if (head == null) {
            return null;
        }

        int carry = 0;
        ListNode current = head;
        ListNode prev = null;

        // Traverse the list in reverse order and double the values
        while (current != null) {
            int newVal = current.val * 2 + carry;
            carry = newVal / 10;
            current.val = newVal % 10;

            prev = current;
            current = current.next;
        }

        // If there's a remaining carry, add a new node for it
        if (carry > 0) {
            ListNode newNode = new ListNode(carry);
            prev.next = newNode;
        }

        return reverseList(head);
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }
}