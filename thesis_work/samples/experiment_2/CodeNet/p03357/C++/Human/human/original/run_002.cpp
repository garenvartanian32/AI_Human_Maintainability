#include <bits/stdc++.h>
using namespace std;
const int N = 4002;
int dp[N][N],cost[N][N],n,rem[2] = {1,1};
pair<int,int>a[2][N];
char c;
int main(){
	//freopen("readin.txt","r",stdin);
	scanf("%d",&n);
	for(int i = 1;i <= 2*n;i++){
		scanf(" %c",&c);
		scanf("%d",&a[c == 'B'][rem[c == 'B']].first);
		a[c == 'B'][rem[c == 'B']++].second = i;
	}
	sort(a[0] + 1,a[0] + n + 1);
	sort(a[1] + 1,a[1] + n + 1);
	for(int k = 0;k < 2;k++)
		for(int i = 1;i <= n;i++){
			for(int j = 1;j < i;j++)if(a[k][i].second < a[k][j].second)cost[i + k*n][0]++;
			for(int j = 1;j <= n;j++)cost[i + k*n][j] = cost[i + k*n][j - 1] + (a[!k][j].second > a[k][i].second);
		}
	for(int i = 0;i <= n;i++)
		for(int j = 0;j <= n;j++)
			dp[i][j] = 1e9 + 7;
	dp[0][0] = 0;
	for(int i = 0;i <= n;i++)
		for(int j = 0;j <= n;j++)
			dp[i + 1][j] = min(dp[i + 1][j],dp[i][j] + cost[i + 1][j]),
			dp[i][j + 1] = min(dp[i][j + 1],dp[i][j] + cost[j + 1 + n][i]);
	printf("%d\n",dp[n][n]);
}