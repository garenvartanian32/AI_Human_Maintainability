#include <bits/stdc++.h>
using namespace std;
using i64 = long long;
#define rep(i,s,e) for(i64 (i) = (s);(i) < (e);(i)++)
#define all(x) x.begin(),x.end()

template<class T>
static inline std::vector<T> ndvec(size_t&& n, T val) { return std::vector<T>(n, std::forward<T>(val)); }
template<class... Tail>
static inline auto ndvec(size_t&& n, Tail&&... tail) {
  return std::vector<decltype(ndvec(std::forward<Tail>(tail)...))>(n, ndvec(std::forward<Tail>(tail)...));
}
template<class Cond> struct chain {
  Cond cond; chain(Cond cond) : cond(cond) {}
  template<class T> bool operator()(T& a, const T& b) const { if(cond(a, b)) { a = b; return true; } return false; }
};
template<class Cond> chain<Cond> make_chain(Cond cond) { return chain<Cond>(cond); }

void solve() {
  i64 N;
  cin >> N;
  vector<i64> A(N);
  rep(i,0,N) cin >> A[i];;
  string S;
  cin >> S;
  set<i64> bases;
  for(int i = N; i --> 0;) {
    if(S[i] == '0') {
      i64 x = A[i];
      for(auto iter = std::rbegin(bases); iter != std::rend(bases); iter++) {
        x = std::min(x, x ^ (*iter));
      }
      if(x != 0) bases.insert(x);
    }
    else {
      i64 x = A[i];
      for(auto iter = std::rbegin(bases); iter != std::rend(bases); iter++) {
        x = std::min(x, x ^ (*iter));
      }
      if(x != 0) {
        cout << "1" << "\n";
        return;
      }
    }
  }
  cout << "0" << "\n";
}

int main() {
  i64 T;
  cin >> T;
  while(T--) {
    solve();
  }
}
