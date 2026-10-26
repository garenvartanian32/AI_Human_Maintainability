class Solution {
public:    
    string finalString(string s) {
        string res = "";
        for (int i = 0; i < s.length(); i++) {
            res += s[s.length() - i - 1];
        }
        return res;
    }
};