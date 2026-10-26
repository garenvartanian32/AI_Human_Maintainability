class Solution:
    def doubleIt(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head:
            return head
        
        curr = head
        while curr:
            curr.val *= 2
            curr = curr.next
        return head