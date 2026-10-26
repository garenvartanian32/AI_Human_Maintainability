#include <bits/stdc++.h>
using namespace std;
int main(){
    string line;
    string pos = "A";
    while (getline(cin, line)) {
        if (line.empty()) continue;
        size_t comma = line.find(',');
        string x = line.substr(0, comma);
        string y = line.substr(comma+1);
        if (pos == x) pos = y;
        else if (pos == y) pos = x;
    }
    cout << pos << endl;
    return 0;
}