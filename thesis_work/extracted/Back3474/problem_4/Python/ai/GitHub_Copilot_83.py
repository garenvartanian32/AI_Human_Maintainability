class Solution:
    def doubleIt(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return None
        if head.next is None:
            head.val = head.val * 2
            return head
        node = head
        while node:
            node.val = node.val * 2
            node = node.next
        node = head
        while node:
            if node.val > 9:
                node.val = node.val % 10
                if node.next is None:
                    node.next = ListNode(1)
                else:
                    node.next.val += 1
            node = node.next
        return head