/// A node of a singly linked list holding one decimal digit.
struct ListNode {
    int val;
    ListNode* next;

    ListNode() : val(0), next(nullptr) {}
    explicit ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode* next) : val(x), next(next) {}
};

/**
 * Doubles the number represented by a singly linked list of digits.
 */
class Solution {
public:
    /**
     * Returns the list representing twice the original number.
     *
     * Doubling has to propagate carries from the least significant digit
     * backwards, so the recursion doubles on the way out of the call stack.
     */
    ListNode* doubleIt(ListNode* head) {
        const int overflow = doubleFrom(head);

        if (overflow > 0) {
            return new ListNode(overflow, head);
        }
        return head;
    }

private:
    /// Doubles `node` and everything after it; returns the carry produced.
    static int doubleFrom(ListNode* node) {
        if (node == nullptr) {
            return 0;
        }

        const int carryIn = doubleFrom(node->next);
        const int doubled = node->val * 2 + carryIn;

        node->val = doubled % 10;
        return doubled / 10;
    }
};