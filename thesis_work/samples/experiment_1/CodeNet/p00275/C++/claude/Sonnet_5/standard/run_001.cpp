#include <bits/stdc++.h>
using namespace std;
int main(){
    int n;
    while (cin >> n && n != 0) {
        string cards;
        cin >> cards;
        vector<int> hands(n, 0);
        int field = 0;
        for (size_t i = 0; i < cards.size(); i++) {
            int p = i % n;
            char c = cards[i];
            if (c == 'M') hands[p]++;
            else if (c == 'S') { field += hands[p] + 1; hands[p] = 0; }
            else if (c == 'L') { hands[p] += field + 1; field = 0; }
        }
        sort(hands.begin(), hands.end());
        for (int h : hands) cout << h << ' ';
        cout << field << "\n";
    }
    return 0;
}