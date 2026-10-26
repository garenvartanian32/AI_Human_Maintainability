#include <bits/stdc++.h>
using namespace std;

int main() {
    long long n;
    cin >> n;
    long long total = 0;
    for (long long d = 1; d * d <= n; d++) {
        if (n % d == 0) {
            set<long long> divisors = {d, n / d};
            for (long long dd : divisors) {
                long long m = dd - 1;
                if (m >= 1) {
                    long long q = n / dd;
                    if (q < m) total += m;
                }
            }
        }
    }
    cout << total << endl;
    return 0;
}