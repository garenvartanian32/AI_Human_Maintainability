#include<iostream>
#include<vector>
#include<string>
#include<cstring>
#include<algorithm>
#include<map>
#include<set>
#include<cmath>
#include<cassert>
#include<queue>

using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef vector<int> vi;

const int MOD = 1e9+7;
const int MAXN = 111;
const int MAXA = 256;

int dp[MAXN][MAXN][MAXA];

int main() {
	int n, k;
	cin >> n >> k;
	vector<int> A(n);
	for (int i = 0; i < n; i++)
		cin >> A[i];
	dp[0][0][0] = 1;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j <= i; j++) {
			for (int a = 0; a < MAXA; a++) {
				(dp[i+1][j][a] += dp[i][j][a]) %= MOD;
				(dp[i+1][j+1][a^A[i]] += dp[i][j][a]) %= MOD;
			}
		}
	}
	ll ans = 0;
	ll fact = 1;
	for (int i = 1; i <= n; i++) {
		(fact *= i) %= MOD;
		(ans += fact * dp[n][i][k]) %= MOD;
	}
	cout << ans << endl;
	return 0;
}
