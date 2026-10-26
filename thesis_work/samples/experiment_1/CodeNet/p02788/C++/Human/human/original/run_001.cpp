#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define rep(i,a,b) for(ll i=a;i<=b;i++)
const ll max_n = 2e5+5;

int main() {
    // your code goes here
    ll n,d,a,x[max_n],h[max_n],r[max_n],l[max_n],sum[max_n],pb=1,ans=0,attack;
    vector<pair<ll,ll>> v;
    vector<ll> vx;
    cin >> n >> d >> a;
    d*=2;
    rep(i,0,n-1) {
        cin >> x[i] >> h[i];
        if(h[i]%a==0) h[i]/=a;
        else h[i]=(h[i]-h[i]%a)/a+1;
        v.push_back({x[i],h[i]});
        vx.push_back(x[i]);
    }
    v.push_back({-1,0});
    vx.push_back(-1);
    sort(v.begin(),v.end());
    sort(vx.begin(),vx.end());
    rep(i,1,n) {
        x[i]=v[i].first;
        h[i]=v[i].second;
    }
    rep(i,1,n) {
        auto it=upper_bound(vx.begin(),vx.end(),x[i]+d);
        it--;
        r[i]=(it-vx.begin());
    }
    rep(i,1,n) {
        while(pb<=r[i]){
            l[pb]=i;
            pb++;
        }
    }
    sum[0] = 0;
    rep(i,1,n) {
        attack = h[i] - min(sum[i-1] - sum[l[i]-1], h[i]);
      	sum[i] = sum[i-1] + attack;
        ans += attack;
    }
    cout << ans << endl;
    return 0;
}