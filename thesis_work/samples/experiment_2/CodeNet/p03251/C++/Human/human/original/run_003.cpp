#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define INF 1LL<<62
#define inf 1000000007 


int main() {
	ll n,m,a=-100,b=100,A,B;
	cin>>n>>m;
	cin>>A>>B;
	a=max(a,A);b=min(B,b);
	for(int i=0;i<n;i++){
		cin>>A;
		a=max(a,A);
	}
	for(int i=0;i<m;i++){
		cin>>B;
		b=min(b,B);
	}
	cout << (a<b? "No War":"War");
	return 0;
}