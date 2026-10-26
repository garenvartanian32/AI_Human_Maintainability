#include <bits/stdc++.h>
using namespace std;
string solve(string s){
    map<int,char> chain;
    int pos=0;
    chain[0]=s[0];
    int i=1, n=s.size();
    while (i<n) {
        if (i+1<n && s[i]=='-' && s[i+1]=='>') {
            pos++;
            chain[pos]=s[i+2];
            i+=3;
        } else {
            pos--;
            chain[pos]=s[i];
            i+=1;
        }
    }
    int lo=chain.begin()->first, hi=chain.rbegin()->first;
    string res;
    for (int p=lo;p<=hi;p++) res+=chain[p];
    return res;
}
int main(){
    int n;
    cin >> n;
    cin.ignore();
    for (int i=0;i<n;i++){
        string s;
        getline(cin, s);
        cout << solve(s) << "\n";
    }
    return 0;
}