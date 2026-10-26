#include <bits/stdc++.h>
using namespace std;

string parseRecord(const string &s) {
    map<char, char> nxt, prv;
    set<char> known;
    char first = s[0];
    known.insert(first);
    char curPrev = first;
    int i = 1;
    int len = s.size();
    while (i < len) {
        bool back = false;
        if (i + 1 < len && s[i] == '-' && s[i+1] == '>') {
            back = true;
            i += 2;
        }
        char cur = s[i];
        i++;
        known.insert(cur);
        if (back) {
            nxt[curPrev] = cur;
            prv[cur] = curPrev;
        } else {
            prv[curPrev] = cur;
            nxt[cur] = curPrev;
        }
        curPrev = cur;
    }
    char head = 0;
    for (char c : known) {
        if (prv.find(c) == prv.end()) { head = c; break; }
    }
    string result;
    char cur = head;
    while (true) {
        result += cur;
        auto it = nxt.find(cur);
        if (it == nxt.end()) break;
        cur = it->second;
    }
    return result;
}

int main() {
    int n;
    cin >> n;
    cin.ignore();
    string out;
    for (int i = 0; i < n; i++) {
        string s;
        getline(cin, s);
        // trim whitespace
        while (!s.empty() && (s.back() == '\r' || s.back() == '\n' || s.back() == ' ')) s.pop_back();
        out += parseRecord(s) + "\n";
    }
    cout << out;
    return 0;
}