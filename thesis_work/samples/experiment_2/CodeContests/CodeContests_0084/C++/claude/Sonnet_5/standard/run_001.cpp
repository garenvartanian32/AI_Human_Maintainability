#include <bits/stdc++.h>
using namespace std;

int main() {
    int n, k, a, b, c, d;
    cin >> n >> k >> a >> b >> c >> d;

    if (n == 4 || (n + 1) > k) {
        cout << -1 << endl;
        return 0;
    }

    set<int> special = {a, b, c, d};
    vector<int> middle;
    for (int x = 1; x <= n; x++) {
        if (!special.count(x)) middle.push_back(x);
    }

    string p1 = to_string(a) + " " + to_string(c);
    for (int x : middle) p1 += " " + to_string(x);
    p1 += " " + to_string(d) + " " + to_string(b);

    string p2 = to_string(c) + " " + to_string(a);
    for (int x : middle) p2 += " " + to_string(x);
    p2 += " " + to_string(b) + " " + to_string(d);

    cout << p1 << "\n" << p2 << "\n";
    return 0;
}