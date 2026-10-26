#include <algorithm>
#include <cstdio>
#include <functional>
#include <iostream>
#include <cfloat>
#include <climits>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <map>
#include <unordered_map>
#include <queue>
#include <set>
#include <sstream>
#include <stack>
#include <string>
#include <time.h>
#include <vector>
using namespace std;

int N, X;
int L[1<<20], R[1<<20], B[1<<20];
long long min_score = 0;
vector<pair<long long, int> > v;
vector<long long> csum(1<<20);

bool possible(long long num_study_hrs) {

  if(num_study_hrs == 0) return (min_score >= 0);

  int num_complete = num_study_hrs / X;
  int partial = num_study_hrs % X;

  if(partial == 0) return ((csum[num_complete-1] + min_score) >= 0);

  for(int i = 0; i < N;i++) {
    long long tsr = 0;
    if(partial <= B[v[i].second]) tsr = partial*1LL*L[v[i].second];
    else tsr = B[v[i].second]*1LL*L[v[i].second]
               + (partial-B[v[i].second])*1LL*R[v[i].second];

    if(num_complete != 0) {
      if(i <= (num_complete-1))
        tsr = csum[num_complete] - v[i].first + tsr;
      else
        tsr = csum[num_complete-1]  + tsr;
    }

    if((tsr + min_score) >= 0) return true;
  }

  return false;
}

int main() {
  //freopen("input.in","r",stdin);
  //freopen("output.out","w",stdout);

  cin >> N >> X;
  for(int i = 0;i < N;i++) {
    cin >> B[i] >> L[i] >> R[i];
    min_score += -(1LL*B[i])*L[i];

    v.push_back({B[i]*1LL*L[i] + (X - B[i])*1LL*R[i], i});
  }

  sort(v.begin(), v.end(), greater<>());
  csum[0] = v[0].first;
  for(int i = 1;i < N;i++) {
     csum[i] = csum[i-1] + v[i].first;
  }

  long long lo = 0, hi = (1LL*N)*X, mid = 0;
  while(lo < hi) {
    mid = (lo + hi) / 2;
    if(possible(mid)) {hi = mid;}//cout<<mid<<endl;}
    else lo = mid + 1;
  }

  cout << lo;// << v[0].first <<" "<<v[1].first<<endl;

  return 0;
}
