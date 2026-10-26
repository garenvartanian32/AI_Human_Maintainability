#include <bits/stdc++.h>
#define pt(sth) cout << sth << "\n"
#define chmax(a, b) {if(a<b) a=b;}
#define chmin(a, b) {if(a>b) a=b;}
#define moC(a, s, b) (a)=((a)s(b)+MOD)%MOD
using namespace std;
typedef long long ll;
typedef pair<ll, ll> P;
static const ll INF=1e18;
static const ll MAX=101010;
static const ll MOD=1e9+7;

/*
 for(i=0; i<N; i++)
   cin >> a[i];
*/

ll N;

void rec(string s, char lc, ll l) {
  if(l>=N) {
    pt(s);
    return;
  }
  
  ll cnt[26]={};
  for(ll i=0; i<s.size(); i++) {
    cnt[s[i]-'a']++;
  }
  
  ll t=0;
  for(ll i=0; i<26; i++) if(cnt[i]) t++;
  
  for(char c='a'; c<='a'+t; c++) {
    rec(s+c, c, l+1);
  }
  
}

int main(void) {
  ll i, j, k;
  cin >> N;
  
  rec("a", 'a', 1);
  
}

