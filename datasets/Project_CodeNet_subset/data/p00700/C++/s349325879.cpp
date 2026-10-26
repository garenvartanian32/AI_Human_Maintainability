#include <iostream>
#include <algorithm>
using namespace std;

int main() {
  int Tc;
  cin >> Tc;
  while(Tc--) {
    pair<int,int> d, p(0,0);
    pair<int,pair<int,int> > maxi(0, make_pair(0,0));
    while(cin >> d.first >> d.second && (d.first|d.second)) {
      p.first += d.first;
      p.second += d.second;
      maxi = max(maxi, make_pair(p.first*p.first+p.second*p.second, p));
    }
    cout << maxi.second.first << " " << maxi.second.second << endl;
  }
  return 0;
}