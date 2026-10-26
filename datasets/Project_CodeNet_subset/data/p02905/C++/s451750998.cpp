#include<bits/stdc++.h>
using namespace std;
typedef long long ll;
const int MAXN=1e6+5;//最大1e9 
const int mod=998244353; 
int prime[MAXN],mu[MAXN],pnum=0;
bool vis[MAXN];//是否是素数 
ll a[MAXN],cnt[MAXN],value[MAXN];
ll inv[MAXN];
ll f[MAXN];
ll qkpow(ll a,ll p,ll mod){
    ll t=1,tt=a%mod;
    while(p){
        if(p&1)t=t*tt%mod;
        tt=tt*tt%mod;
        p>>=1;
    }
    return (t+mod)%mod;
}
ll getInv(ll a){
    return qkpow(a,mod-2,mod);
}
void sieve(){//素数+莫比乌斯函数线性筛 
	memset(vis,true,sizeof(vis)); 
	vis[0]=vis[1]=false; 
	mu[1]=1;
	f[1]=1;
	for(int i=2;i<MAXN;i++)
		inv[i]=getInv(i);
	for(int i=2;i<MAXN;i++){
		if(vis[i]){//i is a prime
			prime[pnum++]=i;
			mu[i]=(-1+mod)%mod;//mu(p)=-1
			f[i]=((1-i)%mod*inv[i]%mod+mod)%mod;//f[p]=(1-p)*inv(p) 
		} 
		for(int j=0;j<pnum&&1LL*i*prime[j]<MAXN;j++){//遍历当前素数 
			int x=prime[j];
			vis[i*x]=false;
			if(i%x==0){//x是i的最小素因子 
				mu[i*x]=0;//mu(p^k)=0,k>1 
				f[i*x]=(f[i]*inv[x]%mod+mod)%mod;
				break;
			}
			else{//i,x互质 
				mu[i*x]=(-mu[i]+mod)%mod;//积性函数性质,mu(x)=-1 
				f[i*x]=(f[i]*inv[x]%mod-f[i]+mod)%mod;
			}
		}
	}
}
int n;
int main(){
	sieve();
	memset(cnt,0,sizeof cnt);
	scanf("%d",&n);
	for(int i=0;i<n;i++){
		scanf("%lld",a+i);
		cnt[a[i]]++;
	}
	for(int i=1;i<=MAXN;i++)
		for(int j=i;j<=MAXN;j+=i)
			value[i]=(value[i]+1ll*j*cnt[j]%mod+mod)%mod;
	ll ans=0;
	for(int i=1;i<=MAXN;i++)
		ans=(ans+1ll*value[i]*value[i]%mod*f[i]%mod+mod)%mod;
	for(int i=0;i<n;i++)
		ans=(ans-a[i]+mod)%mod;
	printf("%lld",1ll*ans*inv[2]%mod);
}