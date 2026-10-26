#include<cmath>
#include<cstdio>
#include<cstring>
#include<iostream>
#include<algorithm>
using namespace std;
#define maxn 4005
typedef long long ll;
typedef long double ld;
typedef pair<int,int> pii;
#define mp make_pair
#define ff first
#define ss second

int n,m;
int op[maxn];
int f[maxn][maxn],w[maxn][maxn][2];
pii a[maxn];
char s[4];

inline int read(){
	int x=0,f=1; char ch=getchar();
	for (;ch<'0'||ch>'9';ch=getchar()) if (ch=='-') f=-1;
	for (;ch>='0'&&ch<='9';ch=getchar()) x=x*10+ch-'0';
	return x*f;
}

int main(){
	n=read();
	for (int i=1;i<=(n<<1);i++){
		scanf("%s",s+1);
		if (s[1]=='B') a[i]=mp(read(),0);
		else a[i]=mp(read(),1);
	}
	for (int i=1;i<=(n<<1);i++){
		for (int j=i+1;j<=(n<<1);j++)
			++w[i][a[j].ff][a[j].ss];
		for (int j=1;j<=n;j++)
			w[i][j][0]+=w[i][j-1][0],w[i][j][1]+=w[i][j-1][1];
	}
	for (int i=1;i<=(n<<1);i++)
		for (int j=1;j<=(n<<1);j++)
			if (i==a[j].ff+a[j].ss*n){op[i]=j; break;}
	memset(f,63,sizeof(f));
	f[0][0]=0;
	for (int i=1;i<=(n<<1);i++)
		for (int j=0;j<=min(i,n);j++){
			int x=i-j; if (x>n) continue;
			if (x) f[i][j]=min(f[i][j],f[i-1][j]+w[op[x]][x][0]+w[op[x]][j][1]);
			if (j) f[i][j]=min(f[i][j],f[i-1][j-1]+w[op[j+n]][j][1]+w[op[j+n]][x][0]);
		}
	printf("%d\n",f[n<<1][n]);
	return 0;
}
