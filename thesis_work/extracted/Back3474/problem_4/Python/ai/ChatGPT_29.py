class Solution:
    def doubleIt(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head:
            return None
        
        carry = 0
        current = head
        prev = None
        
        # Traverse the list in reverse order and double the values
        while current:
            newVal = current.val * 2 + carry
            carry = newVal // 10
            current.val = newVal % 10
            
            prev = current
            current = current.next
        
        # If there's a remaining carry, add a new node for it
        if carry > 0:
            newNode = ListNode(carry)
            prev.next = newNode
        
        return self.reverseList(head)
    
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        prev = None
        current = head
        
        while current:
            nextNode = current.next
            current.next = prev
            prev = current
            current = nextNode
        
        return prev