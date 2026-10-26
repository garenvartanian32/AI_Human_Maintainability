/** A node of a singly linked list holding one decimal digit. */
class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Doubles the number represented by a singly linked list of digits.
 */
class Solution {

    /**
     * Returns the list representing twice the original number.
     *
     * <p>Doubling has to propagate carries from the least significant digit
     * backwards, so the recursion doubles on the way out of the call stack.
     */
    public ListNode doubleIt(ListNode head) {
        int overflow = doubleFrom(head);

        if (overflow > 0) {
            return new ListNode(overflow, head);
        }
        return head;
    }

    /** Doubles {@code node} and everything after it; returns the carry produced. */
    private int doubleFrom(ListNode node) {
        if (node == null) {
            return 0;
        }

        int carryIn = doubleFrom(node.next);
        int doubled = node.val * 2 + carryIn;

        node.val = doubled % 10;
        return doubled / 10;
    }
}