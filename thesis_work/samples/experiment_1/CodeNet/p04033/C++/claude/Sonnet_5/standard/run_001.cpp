#include <bits/stdc++.h>
using namespace std;
int main(){
    long long a,b;
    cin >> a >> b;
    if (a > 0) cout << "Positive" << endl;
    else if (b < 0) {
        long long cnt = b - a + 1;
        cout << (cnt % 2 == 1 ? "Negative" : "Positive") << endl;
    } else {
        cout << "Zero" << endl;
    }
    return 0;
}