#include <bits/stdc++.h>
using namespace std;
int n;
vector<string> result;
vector<int> s;
void rec(int len, int maxc){
    if (len==n) {
        string str;
        for (int i=0;i<len;i++) str += char('a'+s[i]);
        result.push_back(str);
        return;
    }
    for (int c=0;c<=maxc+1;c++){
        s[len]=c;
        rec(len+1, max(maxc,c));
    }
}
int main(){
    cin >> n;
    s.assign(n,0);
    rec(0,-1);
    cout << result.size() << "\n";
    for (auto &r: result) cout << r << "\n";
    return 0;
}