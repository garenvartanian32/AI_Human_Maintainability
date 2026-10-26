#include <bits/stdc++.h>
using namespace std;

int main() {
    int n;
    while (cin >> n) {
        if (n == 0) break;
        int state = 0, count = 0;
        for (int i = 0; i < n; i++) {
            string mv;
            cin >> mv;
            bool raise = (mv[1] == 'u');
            if (state == 0) {
                state = 1;
            } else if (state == 1) {
                if (raise) { state = 2; count++; }
                else { state = 0; }
            } else if (state == 2) {
                state = 3;
            } else {
                if (!raise) { state = 0; count++; }
                else { state = 2; }
            }
        }
        cout << count << "\n";
    }
    return 0;
}