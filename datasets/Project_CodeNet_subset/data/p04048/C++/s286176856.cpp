#include<bits/stdc++.h>
#define ll long long
#define uns using namespace std
uns;
ll ans;
void dfs(ll x,ll y)
{
    if(x%y==0)
	{
        ans+=x*2-y;
        return;
    }
    else
	{
        ans+=(x-x%y)*2;
        dfs(y,x%y);
    }
}
main()
{
	ll n,m;
    scanf("%lld%lld",&n,&m);
    ans=n,n-=m;
    dfs(max(n,m),min(n,m));
    printf("%lld\n",ans);
    return 0;
}