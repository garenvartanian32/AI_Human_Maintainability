#include<bits/stdc++.h>
using namespace std;

using Lint=long long;

int main()
{
   int N; cin>>N;
   vector<int> a(N);
   for(int i=0;i<N;i++) cin>>a[i];

   Lint ans=accumulate(a.begin(),a.end(),0LL)-(Lint)N*(N+1)/2;
   cout<<ans<<endl;
   return 0;
}

