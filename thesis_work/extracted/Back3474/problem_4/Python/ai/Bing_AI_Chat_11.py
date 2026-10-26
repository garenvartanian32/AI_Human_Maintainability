class Solution:
    def doubleIt(self, head: Optional[ListNode]) -> Optional[ListNode]:
        # Convert linked list to integer
        num = 0
        curr = head
        while curr:
            num = num * 10 + curr.val
            curr = curr.next

        # Double the integer
        num *= 2

        # Convert integer to linked list
        dummy = ListNode(0)
        curr = dummy
        for digit in str(num):
            curr.next = ListNode(int(digit))
            curr = curr.next

        return dummy.next