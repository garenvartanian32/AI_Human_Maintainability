class Solution {
public:
    ListNode* doubleIt(ListNode* head) {
        ListNode* temp = head;
         ListNode* temp2 = head;
         while(temp->next!=NULL){
             temp2 = temp2->next;
             temp = temp->next;
         }
         temp2->next = head;
         temp = temp2;
         while(temp->next!=NULL){
             temp->val = temp->val*2;
             if(temp->val>9){
                 temp->val = temp->val%10;
                 temp->next->val = temp->next->val+1;
             }
             temp = temp->next;
         }
         if(temp->val>9){
             temp->val = temp->val%10;
             temp->next = new ListNode(1);
         }
         return head;
    }
};