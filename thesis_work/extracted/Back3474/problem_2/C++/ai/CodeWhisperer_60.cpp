class Solution {
public:
    string finalString(string s) {
        string ans="";
        for(int i=0;i<s.length();i++){
            if(s[i]=='i'){
                reverse(ans.begin(),ans.end());
                ans="";
                continue;
            }
            ans+=s[i];
        }
        return ans;
    }
};