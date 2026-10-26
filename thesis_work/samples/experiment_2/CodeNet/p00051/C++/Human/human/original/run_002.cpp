#include <iostream>
#include <algorithm>
#include <cstdlib>
#include <string>
#include <functional>
using namespace std;

int solve(string s) {
  string t = s;
  sort(s.begin(), s.end());
  sort(t.begin(), t.end(), greater<char>());
  return atoi(t.c_str()) - atoi(s.c_str());
}

int main() {
  int n;
  cin >> n;
  while(n--) {
    string str;
    cin >> str;
    cout << solve(str) << endl;
  }
}