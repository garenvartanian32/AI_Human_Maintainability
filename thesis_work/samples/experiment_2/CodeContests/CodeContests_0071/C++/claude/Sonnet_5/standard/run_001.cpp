#include <bits/stdc++.h>
using namespace std;

int main() {
    string s;
    int n;
    getline(cin, s);
    cin >> n;
    string out;
    for (char c : s) {
        char base = tolower(c);
        int rank = base - 'a' + 1;
        if (rank <= n) out += toupper(base);
        else out += base;
    }
    cout << out << endl;
    return 0;
}