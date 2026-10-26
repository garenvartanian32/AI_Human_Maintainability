#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
const ll MOD=1000000007;


int main(){
  string S;cin>> S;
  string ABC="ABC";
  int N=S.size();

  ll dp[N+1][4];

  for (int j=0;j<4;++j){
    dp[N][j]=(j==3)?1:0;
  }

  for (int n=N-1;n>=0;n--){
    for (int j=0;j<4;++j){
      ll m=(S[n]=='?')? 3:1;
      dp[n][j]=m*dp[n+1][j];
      if((j!=3)&&(S[n]=='?'||S[n]==ABC[j])){
        dp[n][j]+=dp[n+1][j+1];
      }
      dp[n][j]%=MOD;
    }
  }


  cout << dp[0][0]<< endl;
  return 0;
}