class Solution {
public:
    ListNode* doubleIt(ListNode* head) {
        ListNode* current = head;
        int carry = 0;
        while (current != nullptr) {
            int sum = current->val * 2 + carry;
            current->val = sum % 10;
            carry = sum / 10;
            if (current->next == nullptr && carry > 0) {
                current->next = new ListNode(carry);
                break;
            }
            current = current->next;
        }
        return head;
    }
};