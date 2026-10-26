#include<iostream>
#include<algorithm>
#include<vector>
#include<string>
#include<cmath>
#include<map>
#include<stack>
#include<queue>
#include<bitset>

#define ipair pair<int,int>
#define rep(i,n) for(i=0;i<n;i++)

using namespace std;

typedef long long int lli;
typedef vector<int> vi;

int main() {
	map<int, int>m;
	int i, j, k, s;

	cin >> s;
	k = 1;
	i = s;
	while (m.find(i) == m.end()) {
		m.insert(make_pair(i, k));
		k++;
		if (i % 2 == 0) {
			i /= 2;
		}
		else {
			i = i * 3 + 1;
		}
	}

	cout << k;

	return 0;
}