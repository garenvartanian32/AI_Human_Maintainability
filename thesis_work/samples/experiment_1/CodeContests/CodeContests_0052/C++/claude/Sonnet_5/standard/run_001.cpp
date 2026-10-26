#include <bits/stdc++.h>
using namespace std;
int main(){
    int n;
    string out;
    while (cin >> n && n != 0) {
        int count = 0;
        for (int i=0;i<n;i++){
            string s;
            cin >> s;
            count++;
        }
        out += to_string(count) + "\n";
    }
    cout << out;
    return 0;
}