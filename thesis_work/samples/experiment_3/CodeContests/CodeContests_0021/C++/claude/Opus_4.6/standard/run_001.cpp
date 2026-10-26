#include <bits/stdc++.h>
using namespace std;

int main() {
    long long a, b;
    cin >> a >> b;
    long long total = a;
    long long leftover = a;
    while (leftover >= b) {
        long long add = leftover / b;
        total += add;
        leftover = leftover % b + add;
    }
    cout << total << endl;
    return 0;
}