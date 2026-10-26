//C 
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int raw[100000][10],dp[100000][10];
int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n,m,k;
	cin >> n;
	for(int i = 1;i <= n;i++){
		for(int j = 1;j <= 3;j++)cin >> raw[i][j];
	}
	ll ans = 0;
	for(int i = 1;i <= n;i++){
		for(int j = 1;j <= 3;j++){
			for(int k = 1;k <= 3;k++)if(k != j){
				dp[i][j] = max(dp[i-1][k],dp[i][j]);
			}
			dp[i][j] += raw[i][j];
		}
	}
	cout << max({dp[n][1],dp[n][2],dp[n][3]});
	return 0;
}