class Solution:
    def doubleIt(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return None
        
        head.val = head.val * 2
        
        if head.next is None:
            return head
        
        head.next = self.doubleIt(head.next)
        
        if head.val > 9:
            head.val = head.val - 10
            head.next.val = head.next.val + 1
            
        return head