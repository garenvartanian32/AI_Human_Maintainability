#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define vvi vector<vector<int>>
#define vec vector
#define pq priority_queue
#define all(v) (v).begin(), (v).end()
#define rep(i, n) for (int (i) = (0); (i) < (n); ++(i))
#define repp(i, m, n) for (int (i) = (m); (i) < (n); ++(i))
#define debug(x) cerr << #x << ": " << x << endl;
#define debug2(x, y) cerr<<"("<<#x<<", "<<#y<<") = "<<"("<<x<<", "<<y<<")"<<endl;
#define debug3(x, y, z) cerr<<"("<<#x<<", "<<#y<<", "<<#z<<") = "<<"("<<x<<", "<<y<<", "<<z<<")"<<endl;
#define debugB(x, y) cerr<<#x<<": "<<bitset<y>(x) << endl;
#define line() cerr << "---------------" << endl;

const int dx[] = {1, -1, 0, 0};
const int dy[] = {0, 0, -1, 1};

template<typename T>
void printA(vector<T> &printArray, char between = ' ') {
   int paSize = printArray.size();
   for (int i = 0; i < paSize; i++) { cerr << printArray[i] << between;}
   if (between != '\n') {cerr << endl;}
}

// ------------------------------------------------------------------------------------------
const int INF = 1e9+1000;
ll n, a, b;
ll h[111111];

int main() {
   cin >> n >> a >> b;
   rep (i, n) cin >> h[i];

   ll c = a - b;
   ll left = 0, right = INF;
   while (left+1 < right) {
      ll mid = (left+right)/2;
      ll t = 0;
      rep (i, n) {
         ll x = max(h[i]-b*mid, 0LL);
         t += (x+c-1)/c;
      }
      if (t <= mid) {
         right = mid;
      } else {
         left = mid;
      }
   }
   cout << right << endl;

   return 0;
}