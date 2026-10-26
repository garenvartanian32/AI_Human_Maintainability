#include <bits/stdc++.h>
using namespace std;

int main() {
    string s;
    cin >> s;
    vector<int> nums;
    stringstream ss(s);
    string token;
    while (getline(ss, token, '+')) {
        nums.push_back(stoi(token));
    }
    sort(nums.begin(), nums.end());
    string out;
    for (size_t i = 0; i < nums.size(); i++) {
        if (i > 0) out += '+';
        out += to_string(nums[i]);
    }
    cout << out << endl;
    return 0;
}