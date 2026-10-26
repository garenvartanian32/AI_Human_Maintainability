#include<bits/stdc++.h>
using namespace std;

typedef long long ll;
const int N = 2e5+ 7;
const int inf = 1e9 + 7;
const int mod = 1e9 + 7;
#define fi first
#define se second
#define rep(i,a,n) for(int i = a; i <= n; i++)
#define per(i,a,n) for(int i = n; i >= a; i--)
#define pb push_back
#define mp make_pair
typedef pair<int,int> P;

int main()
{
	int a, b,c,d,e;
	scanf("%d%d%d%d%d",&a,&b,&c,&d,&e);
	int x=c-a,y=d-b;
	if(y<0){
        y+=60;
        x--;
	}
	printf("%d\n",x*60+y-e);
}
