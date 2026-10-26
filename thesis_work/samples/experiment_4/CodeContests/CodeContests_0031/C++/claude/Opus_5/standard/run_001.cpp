#include <bits/stdc++.h>
using namespace std;

int main() {
    int t, m;
    cin >> t >> m;
    cin.ignore();
    vector<int> mem(m, 0);
    unordered_set<int> active;
    int nextId = 1;
    string out;
    for (int i = 0; i < t; i++) {
        string line;
        getline(cin, line);
        istringstream iss(line);
        string cmd;
        iss >> cmd;
        if (cmd == "alloc") {
            int n;
            iss >> n;
            int found = -1, run = 0;
            for (int j = 0; j < m; j++) {
                if (mem[j] == 0) {
                    run++;
                    if (run == n) { found = j - n + 1; break; }
                } else {
                    run = 0;
                }
            }
            if (found == -1) {
                out += "NULL\n";
            } else {
                for (int j = found; j < found + n; j++) mem[j] = nextId;
                active.insert(nextId);
                out += to_string(nextId) + "\n";
                nextId++;
            }
        } else if (cmd == "erase") {
            int x;
            iss >> x;
            if (active.count(x)) {
                for (int j = 0; j < m; j++) if (mem[j] == x) mem[j] = 0;
                active.erase(x);
            } else {
                out += "ILLEGAL_ERASE_ARGUMENT\n";
            }
        } else if (cmd == "defragment") {
            vector<int> vals;
            for (int v : mem) if (v != 0) vals.push_back(v);
            for (int j = 0; j < m; j++) mem[j] = (j < (int)vals.size()) ? vals[j] : 0;
        }
    }
    cout << out;
    return 0;
}