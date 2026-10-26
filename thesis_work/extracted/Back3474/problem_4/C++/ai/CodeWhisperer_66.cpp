class Solution {
public:
    ListNode* doubleIt(ListNode* head) {
        if(head == NULL)
            return NULL;
        ListNode* temp = head;
        while(temp->next != NULL){
            temp->val *= 2;
            temp = temp->next;
            if(temp->next != NULL) {
                temp->val *= 2;
                temp = temp->next;
                continue;
            }
            else {
                break;
            }
        }
        return head;
    }
};