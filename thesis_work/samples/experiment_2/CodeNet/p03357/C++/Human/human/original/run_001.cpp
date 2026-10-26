#include <bits/stdc++.h>

using namespace std;

#define SZ(v) ((int)(v).size())
#define ALL(v) (v).begin(),(v).end()
#define one first
#define two second
typedef long long ll;
typedef pair<int, int> pi;
const int INF = 0x3f2f1f0f;
const ll LINF = 1ll * INF * INF;

const int MAX_N = 2e3 + 10;

struct BIT{
	vector<int> val;
	BIT(int n) {
		val = vector<int>(n+10, 0);
	}
	void update(int v, int k) {for(v+=5; v<SZ(val); v+=v&-v) val[v] += k;}
	int getSum(int v) {int res=0; for(v+=5; v>0; v-=v&-v) res += val[v]; return res;}
	int getSum(int a, int b) {return getSum(b) - getSum(a-1);}
};

int N, Ix[2][MAX_N];
ll Dy[MAX_N][MAX_N];
int main() {
	cin >> N;
	for(int i=1; i<=2*N; i++) {
		char s[5]; int v; scanf("%s%d", s, &v);
		Ix[s[0] == 'B'][v] = i;
	}
	BIT up = BIT(2*N);
	BIT lf = BIT(2*N);
	for(int i=0; i<=N; i++) {
		if(i) lf.update(Ix[0][i], 1);
		for(int j=0; j<=N; j++) {
			if(i+j == 0) continue;
			Dy[i][j] = LINF;
			if(i && j) up.update(Ix[1][j], 1);

			if(i) Dy[i][j] = min(Dy[i][j], Dy[i-1][j] + abs( (i+j) - (Ix[0][i] + up.getSum(Ix[0][i], 2*N) ) ) );
			if(j) Dy[i][j] = min(Dy[i][j], Dy[i][j-1] + abs( (i+j) - (Ix[1][j] + lf.getSum(Ix[1][j], 2*N) ) ) );

			if(j) lf.update(Ix[1][j], 1);
		}
		for(int j=1; j<=N; j++) {
			if(i) up.update(Ix[1][j], -1);
			if(j) lf.update(Ix[1][j], -1);
		}
		if(i) up.update(Ix[0][i], 1);
	}
	printf("%lld\n", Dy[N][N]);
	return 0;
}

