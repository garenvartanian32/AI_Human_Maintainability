#include <bits/stdc++.h>
using namespace std;

int main() {
    long long n;
    cin >> n;
    long long total = 0;
    for (int i = 0; i < n; i++) {
        long long v;
        cin >> v;
        total += v;
    }
    total -= n * (n + 1) / 2;
    cout << total << endl;
    return 0;
}