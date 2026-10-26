# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def doubleIt(self, head):
        vals = []
        node = head
        while node:
            vals.append(str(node.val))
            node = node.next
        num = int(''.join(vals)) * 2
        s = str(num)
        dummy = ListNode(0)
        cur = dummy
        for c in s:
            cur.next = ListNode(int(c))
            cur = cur.next
        return dummy.next