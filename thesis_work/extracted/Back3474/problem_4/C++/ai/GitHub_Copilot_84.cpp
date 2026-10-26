class Solution {
public:
    ListNode* doubleIt(ListNode* head) {
        ListNode* temp = head;
        ListNode* prev = NULL;
        while(temp != NULL) {
            temp->val *= 2;
            if(temp->val > 9) {
                if(prev == NULL) {
                    ListNode* newHead = new ListNode(1);
                    newHead->next = head;
                    head = newHead;
                    temp->val -= 10;
                } else {
                    prev->val += 1;
                    temp->val -= 10;
                }
            }
            prev = temp;
            temp = temp->next;
        }
        return head;
    }
};