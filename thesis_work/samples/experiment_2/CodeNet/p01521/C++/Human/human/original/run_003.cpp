#include<iostream>

using namespace std;

int main() {
  string str;
  cin >> str;
  if (str[0] == 'o' || str[str.size() - 1] == 'o') cout << "o" << endl;
  else cout << "x" << endl;
}