#include "iostream"
#include "algorithm"
#include "string"
#include "vector"
#include "cmath"
#include "bitset"
#include "queue"
#include "functional"

#define lp(n) for (int i = 0; i < n; i++)
#define LP(n,i) for (int i = 0; i < n; i++)
#define mod 1000000007
#define sp ' '
#define intmax 2147483647
#define intmin -2147483647
#define llmax 9223372036854775807
#define nyan "(=^･ω･^=)"
typedef long long ll;
using namespace std;

int n, m, a, b, c, mem, cnt;
vector<pair<int, int>>gp[100];
pair<int, bool>djk[100];
pair<int, int>mem2;

/*
ll gcd(ll x, ll y) {
	if (y == 0) return x;
	return gcd(y, x%y);
}
ll lcm(ll x, ll y) {
	ll g = gcd(x, y);
	return x / g * y;
}*/



int main(){
	cin >> n >> m;
	lp(m) {
		cin >> a >> b >> c;
		gp[a - 1].push_back(make_pair(b - 1, c));
		gp[b - 1].push_back(make_pair(a - 1, c));
	}
	cnt = 0;
	lp(n) {
		mem = i;
		LP(n, j)
			djk[j] = make_pair(intmax, false);
		djk[i] = make_pair(0, true);
		LP(n, j) {
			LP(gp[mem].size(), k) {
				djk[gp[mem][k].first].first = min(djk[gp[mem][k].first].first, djk[mem].first + gp[mem][k].second);
			}
			mem2.first = intmax;
			LP(n, k) {
				if (!djk[k].second&&djk[k].first < mem2.first)
					mem2 = make_pair(djk[k].first, k);
			}
			djk[mem2.second].second = true;
			mem = mem2.second;
		}
		LP(gp[i].size(), j) {
			if (djk[gp[i][j].first].first != gp[i][j].second)
				cnt++;
		}
	}
	cout << cnt / 2 << endl;
	return 0;
}