/*
 * Twirling Robot
 * http://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=1156&lang=jp
 */

#include <bits/stdc++.h>

using namespace std;

#define LOG(...) fprintf(stderr, __VA_ARGS__)
//#define LOG(...)
#define FOR(i, a, b) for(int i = (int)(a); i < (int)(b); ++i)
#define REP(i, n) for(int i = 0; i < (int)(n); ++i)
#define ALL(a) (a).begin(), (a).end()
#define RALL(a) (a).rbegin(), (a).rend()
#define EXIST(s, e) ((s).find(e) != (s).end())
#define SORT(c) sort(ALL(c))
#define RSORT(c) sort(RALL(c))
#define SQ(n) (n) * (n)

typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef vector<bool> vb;
typedef vector<int> vi;
typedef vector<pii> vpi;
typedef vector<pll> vpl;
typedef vector<ll> vll;
typedef vector<vb> vvb;
typedef vector<vi> vvi;
typedef vector<vll> vvll;

const int INF = (int)1e9;

int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};

int dc[] = {0, 1, 2, 3, 0};

struct P {
  int x, y, w;
  int cost;

  bool operator >(const P& p) const {
    if (cost == p.cost) return x + y > p.x + p.y;
    return cost > p.cost;
  }
};

int main() {
  int w, h;
  int c[4];

  while(cin >> w >> h, w | h) {
    vvi field(h, vi(w, 4));
    REP(y, h){
      REP(x, w){
        cin >> field[y][x];
      }
    }
    REP(i, 4){
      cin >> c[i];
    }

    vector<vvi> G(h, vvi(w, vi(4, INF)));
    priority_queue<P, vector<P>, greater<P>> que;
    que.push({0, 0, 2, 0});
    G[0][0][2] = 0;

    while(!que.empty()) {
      P p = que.top(); que.pop();

      if (p.x == w - 1 && p.y == h - 1) {
        cout << p.cost << endl;
        break;
      }

      if (field[p.y][p.x] != 4) {
        int sw = (p.w + dc[field[p.y][p.x]]) % 4;
        int sx = p.x + dx[sw];
        int sy = p.y + dy[sw];
        int sc = p.cost;
        if (0 <= sx && 0 <= sy && sx < w && sy < h) {
          if (G[sy][sx][sw] > sc) {
            G[sy][sx][sw] = sc;
            que.push({sx, sy, sw, sc});
          }
        }
      }
      REP(i, 4){
        int sw = (p.w + dc[i]) % 4;
        int sx = p.x + dx[sw];
        int sy = p.y + dy[sw];
        int sc = p.cost + c[i];
        if (0 <= sx && 0 <= sy && sx < w && sy < h) {
          if (G[sy][sx][sw] > sc) {
            G[sy][sx][sw] = sc;
            que.push({sx, sy, sw, sc});
          }
        }
      }
    }
  }
}