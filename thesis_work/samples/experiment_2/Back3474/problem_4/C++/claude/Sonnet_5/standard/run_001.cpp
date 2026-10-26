struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* doubleIt(ListNode* head) {
        int carry = dfs(head);
        if (carry > 0) {
            return new ListNode(carry, head);
        }
        return head;
    }
private:
    int dfs(ListNode* node) {
        if (!node) return 0;
        int carry = dfs(node->next);
        int total = node->val * 2 + carry;
        node->val = total % 10;
        return total / 10;
    }
};