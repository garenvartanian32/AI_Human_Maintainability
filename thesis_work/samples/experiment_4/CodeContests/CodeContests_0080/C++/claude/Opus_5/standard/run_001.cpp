#include <bits/stdc++.h>
using namespace std;

int main() {
    string a, b, c;
    getline(cin, a);
    getline(cin, b);
    getline(cin, c);

    int fa[26] = {0}, fb[26] = {0}, fc[26] = {0};
    for (char ch : a) fa[ch - 'a']++;
    for (char ch : b) fb[ch - 'a']++;
    for (char ch : c) fc[ch - 'a']++;

    int maxX = (int) a.size();
    for (int i = 0; i < 26; i++) {
        if (fb[i] > 0) maxX = min(maxX, fa[i] / fb[i]);
    }

    int bestTotal = -1, bestX = 0, bestY = 0;
    for (int x = 0; x <= maxX; x++) {
        int y = (int) a.size();
        for (int i = 0; i < 26; i++) {
            if (fc[i] > 0) {
                int remaining = fa[i] - x * fb[i];
                y = min(y, remaining / fc[i]);
            }
        }
        int total = x + y;
        if (total > bestTotal) {
            bestTotal = total;
            bestX = x;
            bestY = y;
        }
    }

    string result;
    for (int i = 0; i < bestX; i++) result += b;
    for (int i = 0; i < bestY; i++) result += c;
    int used[26];
    for (int i = 0; i < 26; i++) used[i] = bestX * fb[i] + bestY * fc[i];
    for (int i = 0; i < 26; i++) {
        int cnt = fa[i] - used[i];
        result += string(cnt, (char)('a' + i));
    }
    cout << result << endl;
    return 0;
}