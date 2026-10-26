#include <bits/stdc++.h>
using namespace std;
int main(){
    int q;
    cin >> q;
    string out;
    for (int i=0;i<q;i++){
        long long l,r,d;
        cin >> l >> r >> d;
        if (d < l) out += to_string(d) + "\n";
        else out += to_string((r/d+1)*d) + "\n";
    }
    cout << out;
    return 0;
}