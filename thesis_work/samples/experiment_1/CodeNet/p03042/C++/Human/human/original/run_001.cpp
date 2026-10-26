#include <iostream>
#include <string>
#include <algorithm>
#include <map>
#include <vector>
using namespace std;
#define rep(i,s,n)for(int i=s;i<n;i++)
typedef long long ll;

int main() {
	string s; cin >> s;
	ll l = stoi(s.substr(0, 2));
	ll r = stoi(s.substr(2, 2));
	bool a = false;
	bool b = false;

	if (l == 0 || l >= 13 && l <= 99) {
		a = true;
	}

	if (r == 0 || r >= 13 && r <= 99) {
		b = true;
	}

	if (a & b) {
		cout << "NA" << endl;
	}
	else if (!a & b) {
		cout << "MMYY" << endl;
	}
	else if (a & !b) {
		cout << "YYMM" << endl;
	}
	else if (!a & !b) {
		cout << "AMBIGUOUS" << endl;
	}
	return 0;
}