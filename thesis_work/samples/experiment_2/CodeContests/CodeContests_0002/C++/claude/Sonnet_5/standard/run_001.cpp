#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int q;
    cin >> q;
    string out;
    out.reserve(q * 8);
    for (int i = 0; i < q; i++) {
        long long l, r, d;
        cin >> l >> r >> d;
        long long ans;
        if (d < l || d > r) {
            ans = d;
        } else {
            ans = (r / d + 1) * d;
        }
        out += to_string(ans);
        out += '\n';
    }
    cout << out;
    return 0;
}