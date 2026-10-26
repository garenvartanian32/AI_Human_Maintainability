#include <iostream>
#include <vector>
#include <algorithm>

const long long MOD=998244353;
const long long MAXA=1123456;
long long C[MAXA];
long long inv[MAXA];

void tr(long long a) {
  std::cout<<a<<std::endl;
}

int main() {
  inv[0]=inv[1]=1;
  for (int i=2;i<MAXA;++i) {
    inv[i]=MOD-inv[MOD%i]*(MOD/i)%MOD;
  }

  for (int i=1;i<MAXA;++i) {
    C[i]=(C[i]+inv[i])%MOD;
    for (int j=2*i;j<MAXA;j+=i) {
      C[j]=(C[j]-C[i]+MOD)%MOD;
    }
  }
  
  int N;
  std::cin>>N;
  std::vector<long long > A(N);
  for (int i=0;i<N;++i) {
    std::cin>>A[i];
  }
  std::sort(A.begin(), A.end());

  long long ans=0;
  std::vector<long long> cnt(MAXA, 0);
  for (int i=0;i<N;++i) cnt[A[i]]++;
  for (int i=1;i<MAXA;++i) {
    long long s2=0;
    for (int j=i;j<MAXA;j+=i) {
      s2=(s2+cnt[j]*j)%MOD;
    }
    ans=(ans+s2*s2%MOD*C[i]%MOD)%MOD;
  }
  for (int i=0;i<N;++i) ans=(ans-A[i]+MOD)%MOD;
  std::cout<<ans*inv[2]%MOD<<std::endl;

  
  return 0;
}
