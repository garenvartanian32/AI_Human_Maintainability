#include <bits/stdc++.h>

using namespace std;

int main() {
    int h1, m1, h2, m2, k;
    cin >> h1 >> m1 >> h2 >> m2 >> k;
    int t1 = 60 * h1 + m1;
    int t2 = 60 * h2 + m2;
    cout << t2 - t1 - k << "\n";
    return 0;
}
