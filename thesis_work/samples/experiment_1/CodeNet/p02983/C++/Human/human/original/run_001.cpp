#include<iostream>
#include<cstdio>
#include<vector>
#define ll long long 
using namespace std;
int l,r;
ll ans=2019;
int main()
{
	scanf("%d%d",&l,&r);
	if(r-l>2019) printf("0");
	else{
		for(ll i=l;i<=r;i++)
		  for(ll j=i+1;j<=r;j++)
		    ans=min(ans,(ll)i*j%2019);
		printf("%lld",ans);
	}
	return 0;
}