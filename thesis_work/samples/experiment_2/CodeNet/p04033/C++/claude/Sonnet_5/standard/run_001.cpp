#include <bits/stdc++.h>
using namespace std;
int main() {
    long long a, b;
    cin >> a >> b;
    if (a <= 0 && 0 <= b) {
        cout << "Zero" << endl;
    } else if (a > 0) {
        cout << "Positive" << endl;
    } else {
        long long count = b - a + 1;
        cout << (count % 2 == 0 ? "Positive" : "Negative") << endl;
    }
    return 0;
}