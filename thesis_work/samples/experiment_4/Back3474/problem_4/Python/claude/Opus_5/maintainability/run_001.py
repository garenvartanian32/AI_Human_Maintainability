"""Double the number represented by a singly linked list of digits."""


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def doubleIt(self, head: ListNode) -> ListNode:
        """Return the list representing twice the original number.

        Doubling has to propagate carries from the least significant digit
        backwards, so the recursion doubles on the way *out* of the call stack.
        A leading node is prepended only when the final carry overflows.
        """
        overflow = self._double_from(head)

        if overflow:
            return ListNode(overflow, head)
        return head

    def _double_from(self, node: ListNode) -> int:
        """Double ``node`` and everything after it; return the carry produced."""
        if node is None:
            return 0

        carry_in = self._double_from(node.next)
        doubled = node.val * 2 + carry_in

        node.val = doubled % 10
        return doubled // 10