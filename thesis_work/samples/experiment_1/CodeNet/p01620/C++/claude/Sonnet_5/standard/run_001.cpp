#include <bits/stdc++.h>
using namespace std;
int main(){
    string alphabet;
    for (int i=0;i<26;i++) alphabet += char('a'+i);
    for (int i=0;i<26;i++) alphabet += char('A'+i);
    map<char,int> pos;
    for (int i=0;i<(int)alphabet.size();i++) pos[alphabet[i]]=i;
    int n;
    string out;
    while (cin >> n && n != 0) {
        vector<int> keys(n);
        for (auto &k: keys) cin >> k;
        string s;
        cin >> s;
        string result;
        for (size_t i=0;i<s.size();i++){
            int k = keys[i % n];
            int p = ((pos[s[i]] - k) % 52 + 52) % 52;
            result += alphabet[p];
        }
        out += result + "\n";
    }
    cout << out;
    return 0;
}