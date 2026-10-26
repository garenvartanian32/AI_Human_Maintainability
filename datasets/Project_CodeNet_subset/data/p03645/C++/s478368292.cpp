#include <bits/stdc++.h>
#define SORT(x) sort(x.begin(),x.end(),greater<int>())
#define ALL(x) x.begin(),x.end()
#define rep(i, n) for(int i = 0; i < n; i++)
#define reps(i, m, n) for(int i = m; i < n; i++)
#define repr(i, m, n) for(int i = m; i >= n; i--)
#define INF 1000000000000000003 //1000000007
#define PI (acos(-1))
#define MOD 1000000007
using namespace std;
typedef long long ll;

struct edge{ll to;};
struct graph{
  ll N; //ノードの個数
  vector<vector<edge> > G;
  vector<ll> depth;

  graph(ll n){
    N = n;
    G.resize(N);
    depth.resize(N);
    rep(i,N){depth[i] = -1;}
  }
  void add_edge(ll s, ll t){
    G[s].emplace_back((edge){t});
  }

  int bfs(ll s, ll g) {
    deque<ll> dq; //探索予定地 必要ならpairにしてどこから来たのか記録
    dq.push_back(s); // 始点を探索予定地に
    depth[s]=0; // 始点の深さを0に
    while(!dq.empty()) {
      ll v = dq.front();// vは行く元
      dq.pop_front();
      for(auto E : G[v]){ // E つなぐエッジ
        if(depth[E.to]==-1) { // まだ行ってないなら
          depth[E.to]=depth[v]+1;
          dq.emplace_back(E.to);
        }
      }
    }
    return depth[g];
  }
};


void Main(){

  int N,M;
  cin>>N>>M;

  graph g(N);

  rep(i,M){
    int a,b;
    cin>>a>>b;
    g.add_edge(a-1,b-1);
    g.add_edge(b-1,a-1);
  }

  cout<< ( g.bfs(0,N-1)==2 ? "POSSIBLE" : "IMPOSSIBLE" )<<"\n";

}

//-----------------------------------
int main(){
  cin.tie(nullptr);
  ios_base::sync_with_stdio(false);
  cout << fixed << setprecision(0);
  Main();
}
//-----------------------------------
