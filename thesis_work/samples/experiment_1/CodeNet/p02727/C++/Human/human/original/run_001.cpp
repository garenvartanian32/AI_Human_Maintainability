#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int main(){
  ll X,Y,A,B,C;
  cin >> X >> Y >> A >> B >> C;
  vector<ll> p(A),q(B),r(C);
  for(int i=0;i<A;++i) cin >> p[i];
  for(int i=0;i<B;++i) cin >> q[i];
  for(int i=0;i<C;++i) cin >> r[i];
  sort(p.begin(),p.end(),greater<ll>());
  sort(q.begin(),q.end(),greater<ll>());
  priority_queue<ll, vector<ll>, greater<ll>> Q;

  for(ll i=0;i<X;++i) Q.push(p[i]);
  for(ll i=0;i<Y;++i) Q.push(q[i]);

  for(ll i=0;i<C;++i){
    ll q = Q.top();
    if(q <= r[i]){
      Q.pop();
      Q.push(r[i]);
    }
  }
  ll ans=0;
  for(ll i=0;i<X+Y;++i) {
    ans += Q.top();
    Q.pop();
  }
  cout << ans << endl;
}