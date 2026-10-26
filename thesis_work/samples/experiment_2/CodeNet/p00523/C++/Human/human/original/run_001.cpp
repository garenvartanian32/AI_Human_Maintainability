#include<bits/stdc++.h>
using namespace std;
typedef long long int lld;
lld N;
lld A[200010];
lld sum[200010];

bool solve(lld mid){
  for(int i=0;i<N;i++){
    int a = distance(sum,lower_bound(sum+i+1,sum+N+i+1,sum[i] + mid));
    if(a == N + i + 1) continue;
    int b = distance(sum,lower_bound(sum+a+1,sum+N+i+1,sum[a] + mid));
    if(b == N + i + 1) continue;
    int c = distance(sum,lower_bound(sum+b+1,sum+N+i+1,sum[b] + mid));
    if(c == N + i + 1) continue;
    return true;
  }
  return false;
}

int main(){
  cin >> N;
  for(int i=0;i<N;i++){
    cin >> A[i];
    A[N+i] = A[i];
  }
  sum[0] = A[0];
  for(int i=1;i<2*N;i++){
    sum[i] = sum[i-1] + A[i];
  }
  lld left = 0, right = sum[2*N-1] / 3;
  while(left != right){
    lld mid = (left + right + 1) / 2;
    if(solve(mid) == true) left = mid;
    else right = mid - 1;
  }
  cout << left << endl;
}
  