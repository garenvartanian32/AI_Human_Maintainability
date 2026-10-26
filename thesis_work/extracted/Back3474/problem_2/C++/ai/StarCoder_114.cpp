class Solution {
public:
    string finalString(string s) {
    int n=s.length();
    string ans="";
    for(int i=0;i<n;i++){
        if(s[i]=='i'){
            ans=ans.substr(0,ans.length()-1);
        }
        else{
            ans+=s[i];
        }
    }
    return ans;
    }
};