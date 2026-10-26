#include <bits/stdc++.h>
using namespace std;

int main() {
    long long s;
    cin >> s;
    unordered_map<long long, int> seen;
    long long cur = s;
    int i = 1;
    seen[cur] = 1;
    while (true) {
        if (cur % 2 == 0) cur = cur / 2;
        else cur = 3 * cur + 1;
        i++;
        if (seen.count(cur)) {
            cout << i << endl;
            return 0;
        }
        seen[cur] = i;
    }
    return 0;
}