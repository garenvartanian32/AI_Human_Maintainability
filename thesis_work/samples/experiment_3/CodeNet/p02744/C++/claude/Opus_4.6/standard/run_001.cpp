#include <bits/stdc++.h>
using namespace std;

int n;
string buf;
string out;

void bt(int pos, int maxUsed) {
    if (pos == n) {
        out += buf;
        out += '\n';
        return;
    }
    for (int c = 0; c <= maxUsed + 1; c++) {
        buf[pos] = (char)('a' + c);
        bt(pos + 1, max(maxUsed, c));
    }
}

int main() {
    cin >> n;
    buf.resize(n);
    bt(0, -1);
    cout << out;
    return 0;
}