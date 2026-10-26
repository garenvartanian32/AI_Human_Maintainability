#include <cstdio>
#include <vector>
#include <algorithm>
#include <functional>
#include <map>
#include <set>
#include <string>
#include <iostream>
#include <cassert>
#include <cmath>
using namespace std;

const long long MOD = 1e9 + 7;

long long Pow(long long x, long long a) {
	long long res = 1;
	while (a > 0) {
		if (a & 1) res = res * x % MOD;
		x = x * x % MOD;
		a >>= 1;
	}
	return res;
}

int main() {
	int n, k;
	scanf("%d%d", &n, &k);
	vector<vector<vector<long long>>> dp(n + 1, vector<vector<long long>> (n + 2, vector<long long> (2)));
	dp[n][0][0] = 1;
	for (int i = n; i >= 1; i --) {
		for (int j = n; j >= 0; j --) {
			(dp[i - 1][j][0] += dp[i][j][0] + dp[i][j][1]) %= MOD;
			(dp[i - 1][j + 1][1] += dp[i][j][0] + dp[i][j][1]) %= MOD;
			if (j > 0) {
				(dp[i][j - 1][0] += dp[i][j][0]) %= MOD;
			}
		}
	}
	long long ans = dp[1][n - k][0] + dp[1][n - k][1];
	(ans *= (n == k ? 1 : Pow(2, n - k - 1))) %= MOD;
	printf("%lld\n", ans);
        return 0;
}
