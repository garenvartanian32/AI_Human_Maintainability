class Solution {
public:    
    ListNode* doubleIt(ListNode* head) {
        if(head == NULL) return NULL;
        ListNode* cur = head;
        ListNode* pre = NULL;
        ListNode* next = NULL;
        while(cur!= NULL) {
            next = cur->next;
            cur->next = pre;
            pre = cur;
            cur = next;
        }
        cur = pre;
        while(cur!= NULL) {
            cur->val *= 2;
            cur = cur->next;
        }
        return pre;
    }
};