
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef long double ld;
typedef pair<ll, ll> l_l;
typedef pair<int , int> i_i;
typedef vector<ll> vel;
typedef vector<int> vei;
typedef vector<char> vec;
typedef vector<bool> veb;
typedef vector<string> ves;
typedef vector<vector<ll>> ve_vel;
typedef vector<vector<int>> ve_vei;
typedef vector<vector<char>> ve_vec;
typedef vector<vector<bool>> ve_veb;
typedef vector<vector<string>> ve_ves;
#define rep(i,n) for(int i=0;i<(int)(n);i++)
#define rep1(i,n) for(int i=1;i<(int)(n);i++)
#define rep2(i,n) for(int i=2;i<(int)(n);i++)
#define repk(i,k,n) for(int i=k;i<(int)(n);i++)
#define fs first
#define sc second
#define pub push_back
#define pob pop_back
#define all(a) a.begin(),a.end()
#define rall(a) a.rbegin(),a.rend()
#define maxel(a) *max_element(all(a))
#define minel(a) *min_element(all(a))
#define acc accumulate
#define EPS (1e-7)
//#define INF (1e9)
#define PI (acos(-1))
#define mod (1000000007)
typedef long long int64;
const int64 INF = 1LL << 58;
template<class T> bool chmax(T &a, const T &b) { if (a<b) { a=b; return 1; } return 0; }
template<class T> bool chmin(T &a, const T &b) { if (a>b) { a=b; return 1; } return 0; }

//LCA(Lowest Common Ancestor):根付き木の2頂点の共通祖先で最も近いもの
//AOJ(Lowest Common Ancestor)

#define MAX_V 100005
#define MAX_LOG_V 50
vei G[MAX_V]; //隣接リスト表現
int root = 0; //根ノード番号

int parent[MAX_LOG_V][MAX_V]; //親を2^k回辿って到達する頂点
int depth[MAX_V]; //根からの深さ

void dfs(int v, int p, int d) {
    parent[0][v] = p;
    depth[v] = d;
    rep(i,G[v].size()) {
        if(G[v][i] != p) dfs(G[v][i], v, d + 1);
    }
}

//初期化
void init(int V) {
    //parent[0], depthの初期化
    dfs(root, -1, 0);
    //parentの初期化
    for(int k = 0; k + 1 < MAX_LOG_V; k++) {
        for(int v = 0; v < V; v++) {
            if(parent[k][v] < 0) parent[k + 1][v] = -1;
            else parent[k + 1][v] = parent[k][parent[k][v]];
        }
    }
}

//uとvのLCA
int lca(int u, int v) {
    //uとvの深さが同じになるまで親を辿る
    if(depth[u] > depth[v]) swap(u, v);
    rep(k, MAX_LOG_V) {
        if( (depth[v] - depth[u]) >> k & 1 ) {
            v = parent[k][v];
        }
    }
    if(u == v) return u;
    for(int k = MAX_LOG_V - 1; k >= 0; k--) {
        if(parent[k][u] != parent[k][v]) {
            u = parent[k][u];
            v = parent[k][v];
        }
    }
    return parent[0][u];
}

int main(){
	cin.tie(0);
	ios::sync_with_stdio(false);

    int n; cin >> n;
    
    rep(i,n) {
        int k; cin >> k;
        rep(j,k) {
            int c; cin >> c;
            G[i].pub(c);
        }
    }

    init(n);

    int q; cin >> q;
    rep(i,q) {
        int u, v; cin >> u >> v;
        cout << lca(u, v) << endl;
    }
    return 0;
}


