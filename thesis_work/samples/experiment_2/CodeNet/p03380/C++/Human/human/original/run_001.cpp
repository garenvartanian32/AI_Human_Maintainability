#include <iostream>
#include <vector>
#include <map>
#include <string>
#include <cstdio>
#include <algorithm>
#include <cstdlib>
#include <functional>
#include <queue>
#include <utility>
using namespace std;
typedef long long ll;
int main(){
  int n;
  cin >> n;
  vector<ll> a(n);
  for(int i = 0;i < n;i++)cin >> a[i];
  sort(a.begin(),a.end());

  ll a_1,a_2;
  a_1 = a[n-1];
  a_2 = a[0];

  if(n % 2 == 0){
    ll cent = a_1/2;
    ll now_dist = abs(cent - a_2);
    for(int i = 0;i < n-1;i++){
      ll dist = abs(a[i] - cent);
      if(dist < now_dist){
        now_dist = dist;
        a_2 = a[i];
      }
    }
  }
  else{
    ll cent_1 = a_1/2;
    ll cent_2 = (a_1+1)/2;
    ll now_dist = abs(cent_1 - a_2) + abs(cent_2 - a_2);
    for(int i = 0;i < n-1;i++){
      ll dist = abs(cent_1 - a[i]) + abs(cent_2 - a[i]);
      if(dist < now_dist){
        a_2 = a[i];
        now_dist = dist;
      }
    }
  }

  cout << a_1 << " " << a_2 << endl;
}
