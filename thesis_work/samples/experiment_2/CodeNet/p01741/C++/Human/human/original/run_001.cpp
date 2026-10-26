#include <bits/stdc++.h>

using namespace std;

int main(){
  cin.tie(0);
  ios::sync_with_stdio(false);

  double d;cin>>d;
  cout<<  fixed<<setprecision(10)<<max(sqrt(2)*d,(int)d+1.0)<<endl;
}