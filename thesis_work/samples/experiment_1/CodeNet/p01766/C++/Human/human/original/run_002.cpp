#include <algorithm>
#include <cfloat>
#include <climits>
#include <cmath>
#include <complex>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <functional>
#include <iostream>
#include <map>
#include <memory>
#include <queue>
#include <set>
#include <sstream>
#include <stack>
#include <string>
#include <utility>
#include <vector>
 
using namespace std;
 
#define sz size()
#define pb push_back
#define mp make_pair
#define fi first
#define se second
#define all(c) (c).begin(), (c).end()
#define rep(i,a,b) for(int i=(a);i<(b);++i)
#define clr(a, b) memset((a), (b) ,sizeof(a))
 
#define MOD 1000000007

int main(){
  int n;
  cin>>n;
  int mxa = -1;
  int mxb = -1;
  int mna = 325000;
  int mnb = 325000;
  int old_f,old_a,old_t,old_x,old_y;
  cin>>old_f>>old_a>>old_t>>old_x>>old_y;
  rep(i,1,n){
    int f,a,t,x,y;
    cin>>f>>a>>t>>x>>y;
    if(t==old_t&&a!=old_a){
      int l = (x-old_x)*(x-old_x)+(y-old_y)*(y-old_y);
      int ff = f-old_f;
      
      if(t==0){
        if(mxa<l){
          mxa = max(mxa,l);
          mna = ff;
        }
        else if(mxa==l){
          mna = min(mna,ff);
        }
      }
      else{
        if(mxb<l){
          mxb = max(mxb,l);
          mnb = ff;
        }
        else if(mxb==l){
          mnb = min(mnb,ff);
        }
      }
    }
    old_f = f;
    old_a = a;
    old_t = t;
    old_x = x;
    old_y = y;
  }
  if(mxa==-1){
    printf("-1 -1\n");
  }
  else{
    printf("%.8f %.8f\n",pow(mxa,0.5),mna/60.);
  }
  if(mxb==-1){
    printf("-1 -1\n");
  }
  else{
    printf("%.8f %.8f\n",pow(mxb,0.5),mnb/60.);
  }
  return 0;
}