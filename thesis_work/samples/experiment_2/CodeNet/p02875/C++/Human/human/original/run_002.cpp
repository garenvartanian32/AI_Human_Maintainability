#include<bits/stdc++.h>

#define ll long long
#define fornum(A,B,C) for(A=B;A<C;A++)
#define mp make_pair
#define pii pair<int,int>
#define pll pair<ll,ll>

using namespace std;

/////////////////////////////////////////////////////
#define MOD  (ll)998244353

ll moddiv(ll a,ll b){
    a %= MOD;
    b %= MOD;
    for(ll m=MOD-2;m>0;m/=2){
        if(m&1){
            a=a*b%MOD;
        }
        b=b*b%MOD;
    }
    return a;
}

ll Fa[10101010], iFa[10101010];
ll p2[10101010];
ll C(ll a,ll b){
    return Fa[a] * iFa[b] % MOD * iFa[a - b] % MOD;
}

ll N;
ll i, j, k,ans;


int main(){
    scanf("%lld",&N);
    Fa[0] = 1;
    p2[0] = 1;
    fornum(i,0,N){
        Fa[i + 1] = Fa[i] * (i + 1) % MOD;
        p2[i + 1] = p2[i] * 2 % MOD;
    }
    iFa[N] = moddiv(1, Fa[N]);
    for (i = N; i > 0;i--)
    {
        iFa[i - 1] = iFa[i] * i % MOD;
    }

    ans = 1;
    fornum(i,0,N){
        ans = ans * 3 % MOD;
    }
    fornum(i,N/2+1,N+1){
        ans = ans + (MOD - C(N, i) * p2[N - i]) * 2 % MOD;
        ans %= MOD;
    }

    printf("%lld", ans);

    return 0;
}