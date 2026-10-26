#define _GLIBCXX_DEBUG
#include <bits/stdc++.h>
using namespace std;
template<class T> inline bool chmax(T& a,T b){if(a<b){a=b;return 1;} return 0;}
template<class T> inline bool chmin(T& a,T b){if(a>b){a=b;return 1;} return 0;}
#define rep(i,n) for(int i=0;i<(int)(n);i++)
#define all(vec) vec.begin(),vec.end()
typedef long long ll;
typedef pair<ll,ll> pll;
typedef pair<int,int> pii;
typedef tuple<int,int,int> tiii;
const ll mod=1e9+7;
const int inf=1<<29;

int dp[110][110];

int main(){
  int n,m;
  cin >> n >> m;
  rep(i,n)rep(j,n) dp[i][j]=inf;
  rep(i,n) dp[i][i]=0;
  vector<bool> ans(m,1);
  vector<tiii> to(m);
  rep(i,m){
    int a,b,c; cin >> a >> b >> c;
    a--; b--;
    dp[a][b]=c;
    dp[b][a]=c;
    to[i]=tiii(a,b,c);
  }
  rep(k,n)rep(i,n)rep(j,n) chmin(dp[i][j],dp[i][k]+dp[k][j]);
  rep(i,n)rep(j,n){
    rep(k,m){
      int a,b,c;
      tie(a,b,c)=to[k];
      if(dp[i][j]==dp[i][a]+c+dp[b][j]) ans[k]=0;
    }
  }
  int cnt=0;
  rep(i,m){
    if(ans[i]) cnt++;
  }
  cout << cnt << endl;
  
}