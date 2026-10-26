#include<bits/stdc++.h>
using namespace std;

#define MOD 998244353 
#define MAX 310
#define int long long

int power(int x, int n){
  int ans = 1;
  
  while(n){
    if(n&1) ans *= x;
    n >>= 1;
    x *= x;
    x %= MOD;
    ans %= MOD;
  }

  return ans;
}

signed main(){
  static int a[MAX],hoge[MAX][MAX*MAX], hoge2[MAX][MAX*MAX];
  int N, sum = 0, max_ = 0, fuga, temp;
  int ans = 0;
  cin>>N;

  for(int i = 0; i < N; i++){
    cin>>a[i];
    sum += a[i], sum %= MOD;
    max_ = max(max_, a[i]);
  }

  fuga = power(3,N);
  
  hoge[0][0] = 1;
  hoge2[0][0] = 1;

  for(int i = 0; i < N; i++){
    for(int j = 0; j <= sum; j++){
      hoge[i+1][j+a[i]] += hoge[i][j]%MOD;
      hoge[i+1][j] += hoge[i][j]*2%MOD;
      hoge[i+1][j] %= MOD;
      
      hoge2[i+1][j+a[i]] += hoge2[i][j]%MOD;
      hoge2[i+1][j] += hoge2[i][j]%MOD;
      hoge2[i+1][j] %= MOD;
    }
  }
  /*
  for(int j = 0; j <= max_*max_; j++){
    cout<<j<<" "<<hoge[N][j]<<endl;
  }
  */

  for(int i = (sum+1)/2; i <= sum; i++){
    ans += hoge[N][i]*3;
    ans %= MOD;
  }

  if(sum%2 == 0){
    ans = (ans+MOD-hoge2[N][sum/2]*3%MOD)%MOD;
  }

  // cout<<"3^N = "<<fuga<<endl;
  //cout<<ans<<" "<<sum<<endl;

  ans = (fuga - ans + MOD)%MOD;
  
  cout<<ans%MOD<<endl;
  
  return 0;
}
