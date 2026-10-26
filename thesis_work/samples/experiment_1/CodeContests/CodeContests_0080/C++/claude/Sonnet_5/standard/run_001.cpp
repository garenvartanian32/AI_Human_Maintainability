#include <bits/stdc++.h>
using namespace std;
int main(){
    string a,b,c;
    cin >> a >> b >> c;
    int remaining[26] = {0}, cb[26]={0}, cc[26]={0};
    for (char ch: a) remaining[ch-'a']++;
    for (char ch: b) cb[ch-'a']++;
    for (char ch: c) cc[ch-'a']++;
    string used;
    while (true) {
        bool canB=true, canC=true;
        for (int i=0;i<26;i++){ if (remaining[i]<cb[i]) canB=false; if (remaining[i]<cc[i]) canC=false; }
        if (canB) {
            for (int i=0;i<26;i++) remaining[i]-=cb[i];
            used += b;
        } else if (canC) {
            for (int i=0;i<26;i++) remaining[i]-=cc[i];
            used += c;
        } else break;
    }
    for (int i=0;i<26;i++) for (int j=0;j<remaining[i];j++) used += char('a'+i);
    cout << used << endl;
    return 0;
}