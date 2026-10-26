class Solution:
    def doubleIt(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head:
            return head
        if not head.next:
            head.val *= 2
            return head
        prev = head
        curr = head.next
        while curr:
            curr.val *= 2
            prev = curr
            curr = curr.next
        prev.val *= 2
        return head