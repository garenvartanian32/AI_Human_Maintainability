#include <string>
#include <deque>
using namespace std;

class Solution {
public:
    string finalString(string s) {
        deque<char> dq;
        bool normal = true;
        for (char c : s) {
            if (c == 'i') {
                normal = !normal;
            } else {
                if (normal) dq.push_back(c);
                else dq.push_front(c);
            }
        }
        string res;
        if (normal) {
            for (char c : dq) res += c;
        } else {
            for (auto it = dq.rbegin(); it != dq.rend(); ++it) res += *it;
        }
        return res;
    }
};