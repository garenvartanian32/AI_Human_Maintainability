#include <bits/stdc++.h>
using namespace std;
 
int main(){
  long long int k;
  cin >> k;
  double ans = 100.0;
  for(int i=0; i<4000; i++){
    ans *= 1.01;
    long long int tmp = floor(ans);
    if(tmp>=k){
      cout << i+1 << endl;
      return 0;
    }
    ans = tmp;
  }
  return 0;
}
