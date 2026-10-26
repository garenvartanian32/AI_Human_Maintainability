#include<bits/stdc++.h>
using namespace std;
#define ll long long int
void solve()
{
  
  ll a,b,ans=0;
  cin>>a>>b;
  
  ll outlet=1;
  
  while(outlet<b)
  {
      --outlet;
      outlet+=a;
      ans++;
  }
  
  cout<<ans;
  return;
  
}

int main()
{
  solve();
  return 0;
}