#include <bits/stdc++.h>
#define range(i, a, b) for(int i = (a); i < (b); i++)
#define rep(i, a) range(i, 0, a)
using namespace std;

int main() {
	int n;
	map <int, int> mp;
	rep (i, 52 * 2) {
		if (i < 26) {
			mp[i] = 'a' + i;
		} else if (i < 52) {
			mp[i] = 'A' + 26 - (52 - i);
		} else if (i < 26 * 3) {
			mp[i] = 'a' + 26 - (26*3 - i);
		} else {
			mp[i] = 'A' + 26 - (26*4 - i);
		}
	}

	while (cin >> n, n) {
		vector <int> k(n);
		rep (i, n) cin >> k[i];
		string s;
		cin >> s;
		string out = "";

		rep (i, s.size()) {
			int tmp;
			if ('a' <= s[i] && s[i] <= 'z') tmp = 52 + s[i] - 'a';
			else tmp = s[i] - 'A' + 78;
			out.push_back((char)mp[tmp - k[i%n]]);
		}
		cout << out << endl;
	}
	return 0;
}
