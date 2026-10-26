#include <bits/stdc++.h>
#define rep(i, n) for (int i = 0; i < (n); i++)
using namespace std;
using ll = long long;
using P = pair <int, int>;
const double PI = acos(-1);
const ll MOD = 1000000007;

int main() {
  vector<char> alpha = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
  ll N;
  cin >> N;
  vector<char> m(10);
  ll now = N;

  rep(i, 15){
    int r = now % 26;
    //cout << now << endl;
    if(now > 0) {
      if(r != 0){
        m[i] = alpha[r-1];
        now /= 26;
      }
      else{
        m[i] = 'z';
        now =  now / 26 -1;
      }
    }
    else m[i] = '1';
  }

  rep(i,15) {
    if(m[14-i] != '1' )cout << m[14-i];
  }
  cout << endl;


}
