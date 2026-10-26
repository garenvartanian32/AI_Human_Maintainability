#include <bits/stdc++.h>
using namespace std;

int main() {
    char ball = 'A';
    string line;
    while (getline(cin, line)) {
        if (line.empty()) continue;
        char x = line[0];
        char y = line[2];
        if (ball == x) ball = y;
        else if (ball == y) ball = x;
    }
    cout << ball << endl;
    return 0;
}