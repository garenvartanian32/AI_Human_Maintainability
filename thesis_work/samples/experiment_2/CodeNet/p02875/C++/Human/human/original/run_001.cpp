#include <bits/stdc++.h>
using namespace std;
#define mod 998244353
long long pw[5000005],fact[5000005],inv[5000005];
long long pow_log(long long x,int y)
{
	if (!y)
	return 1;
	long long ret=pow_log(x,y/2);
	ret=(ret*ret)%mod;
	if (y%2)
	ret=(ret*x)%mod;
	return ret;
}
int ncr(int n,int r)
{
	return ((fact[n]*inv[r])%mod*inv[n-r])%mod;
}
int main()
{
	int n;
	scanf("%d",&n);
	n/=2;
	fact[0]=1;
	pw[0]=1;
	for (int i=1;i<=n;i++)
	{
		fact[i]=(i*fact[i-1])%mod;
		pw[i]=(2*pw[i-1])%mod;
	}
	inv[n]=pow_log(fact[n],mod-2);
	for (int i=n-1;i>=0;i--)
	inv[i]=((i+1)*inv[i+1])%mod;
	int ans=0,sum=pw[n];
	for (int a=0;a<=n;a++)
	{
		long long tmp=(ncr(n,a)*pw[n-a])%mod;
		ans=(ans+2*tmp*sum-tmp*tmp%mod+mod)%mod;
		if (a!=n)
		{
			sum=(sum+ncr(n,a+1)*pw[n-a-1])%mod;
			sum=(sum-ncr(n,a)*pw[a]%mod+mod)%mod;
		}
	}
	printf("%d",ans);
}