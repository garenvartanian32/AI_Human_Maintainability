#include <bits/stdc++.h>
using namespace std;
int n;
string out_s;
vector<int> s;
void rec(int len, int maxc){
    if (len==n) {
        for (int i=0;i<len;i++) out_s += char('a'+s[i]);
        out_s += "\n";
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
    cout << out_s;
    return 0;
}