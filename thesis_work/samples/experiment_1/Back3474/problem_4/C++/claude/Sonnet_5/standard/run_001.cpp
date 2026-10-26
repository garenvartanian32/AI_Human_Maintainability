#include <bits/stdc++.h>
using namespace std;

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
        string s;
        ListNode* node = head;
        while (node) {
            s += to_string(node->val);
            node = node->next;
        }
        // multiply decimal string by 2
        string result;
        int carry = 0;
        for (int i = s.size()-1; i >= 0; i--) {
            int d = (s[i]-'0')*2 + carry;
            result += char('0' + d % 10);
            carry = d / 10;
        }
        if (carry) result += char('0'+carry);
        reverse(result.begin(), result.end());
        ListNode* dummy = new ListNode(0);
        ListNode* cur = dummy;
        for (char c : result) {
            cur->next = new ListNode(c - '0');
            cur = cur->next;
        }
        return dummy->next;
    }
};