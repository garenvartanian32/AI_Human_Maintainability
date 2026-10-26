#include <bits/stdc++.h>
using namespace std;

int main() {
    int n, m;
    cin >> n >> m;
    unordered_set<int> covered;
    for (int i = 0; i < n; i++) {
        int xi;
        cin >> xi;
        for (int j = 0; j < xi; j++) {
            int y;
            cin >> y;
            covered.insert(y);
        }
    }
    cout << ((int)covered.size() == m ? "YES" : "NO") << endl;
    return 0;
}