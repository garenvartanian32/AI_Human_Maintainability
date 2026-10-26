class Solution {
public:
    ListNode* doubleIt(ListNode* head) {
        ListNode* temp=head;
        while(temp!=NULL){
            ListNode* temp2=temp->next;
            temp->next=new ListNode(temp->val);
            temp->next->next=temp2;
            temp=temp2;
        }
        return head;
    }
};