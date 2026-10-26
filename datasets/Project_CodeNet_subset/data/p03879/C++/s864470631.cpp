#include<iostream>
#include<algorithm>
#include<cstdio>
#include<cmath>
#include<cctype>
#include<math.h>
#include<string>
#include<string.h>
#include<stack>
#include<queue>
#include<vector>
#include<utility>
#include<set>
#include<map>
#include<stdlib.h>
#include<iomanip>

using namespace std;

#define ll long long
#define ld long double
#define EPS 0.0000000001
#define INF 1e9
#define MOD 1000000007
#define rep(i,n) for(int i=0;i<(n);i++)
#define loop(i,a,n) for(i=a;i<(n);i++)
#define all(in) in.begin(),in.end()
#define shosu(x) fixed<<setprecision(x)

typedef vector<int> vi;
typedef pair<int,int> pii;

int main(void) {
  double x1,x2,x3;
  double y1,y2,y3;
  cin>>x1>>y1;
  cin>>x2>>y2;
  cin>>x3>>y3;

  double a=hypot(abs(x1-x2),abs(y1-y2));
  double b=hypot(abs(x2-x3),abs(y2-y3));
  double c=hypot(abs(x3-x1),abs(y3-y1));
  double len=max(a,max(b,c));

  double s=(a+b+c)/2;
  double area=sqrt(s*(s-a)*(s-b)*(s-c));
  double r=area/s;

  double ok=0,ng=len;
  rep(i,100000){
    double x=(ok+ng)/2;
    if(len*(r-x) >= 2*x*r)ok=x;
    else ng=x;
  }

  cout<<shosu(13)<<ok<<endl;
}
