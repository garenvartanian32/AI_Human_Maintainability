#include <bits/stdc++.h>
using namespace std;
int main(){
    string s;
    int n;
    cin >> s >> n;
    string res;
    for (char ch: s) {
        if (isupper(ch)) res += char((ch-'A'+n)%26+'A');
        else if (islower(ch)) res += char((ch-'a'+n)%26+'a');
        else res += ch;
    }
    cout << res << endl;
    return 0;
}