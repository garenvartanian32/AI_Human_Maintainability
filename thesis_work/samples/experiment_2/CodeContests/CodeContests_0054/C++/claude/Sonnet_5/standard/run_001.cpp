#include <bits/stdc++.h>
using namespace std;

int main() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (auto &x : a) cin >> x;
    int swaps = 0;
    for (int i = 0; i < n; i++) {
        int mini = i;
        for (int j = i; j < n; j++) {
            if (a[j] < a[mini]) mini = j;
        }
        if (mini != i) {
            swap(a[i], a[mini]);
            swaps++;
        }
    }
    for (int i = 0; i < n; i++) {
        if (i > 0) cout << " ";
        cout << a[i];
    }
    cout << "\n" << swaps << "\n";
    return 0;
}