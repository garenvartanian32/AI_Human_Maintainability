#include <bits/stdc++.h>
using namespace std;
int main(){
    string s;
    cin >> s;
    string digits;
    for (char c: s) if (c != '+') digits += c;
    sort(digits.begin(), digits.end());
    string out;
    for (size_t i=0;i<digits.size();i++){ if(i>0) out+='+'; out+=digits[i]; }
    cout << out << endl;
    return 0;
}