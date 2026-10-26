class Solution:
    def doubleIt(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return None
        temp = head
        while temp is not None:
            temp.val *= 2
            temp = temp.next
        return head