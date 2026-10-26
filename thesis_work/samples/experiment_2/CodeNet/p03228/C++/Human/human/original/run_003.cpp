#include<bits/stdc++.h>
#define INF 0x7fffffff
using namespace std;
typedef long long ll;
inline ll min(ll x,ll y) {return x<y?x:y;}
inline ll max(ll x,ll y) {return x>y?x:y;}
const int Maxn=1e5+5;
int a[2],k;
int main()
{
	cin>>a[0]>>a[1]>>k;
	for(int i=0;i<k;i++)
	{
		a[!(i&1)]+=a[i&1]/2;
		a[i&1]/=2;
	}
	cout<<a[0]<<" "<<a[1]<<endl;
 	return 0;
}
