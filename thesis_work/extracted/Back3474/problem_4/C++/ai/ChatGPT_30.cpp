class Solution {
public:
    ListNode* doubleIt(ListNode* head) {
        if (!head) {
            return nullptr;
        }

        int carry = 0;
        ListNode* current = head;
        ListNode* prev = nullptr;

        // Traverse the list in reverse order and double the values
        while (current) {
            int newVal = current->val * 2 + carry;
            carry = newVal / 10;
            current->val = newVal % 10;

            prev = current;
            current = current->next;
        }

        // If there's a remaining carry, add a new node for it
        if (carry > 0) {
            ListNode* newNode = new ListNode(carry);
            prev->next = newNode;
        }

        return reverseList(head);
    }

    ListNode* reverseList(ListNode* head) {
        ListNode* prev = nullptr;
        ListNode* current = head;

        while (current) {
            ListNode* nextNode = current->next;
            current->next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }
};