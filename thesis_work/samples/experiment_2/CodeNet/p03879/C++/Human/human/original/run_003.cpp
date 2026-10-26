#include <bits/stdc++.h>
using namespace std;
using Int = long long;
typedef pair<int,int> P; typedef pair<Int,Int> Pl;
const int mod = 1e9+7;
#define END {cout<<ans<<'\n'; return 0;}
#define ALL(v) v.begin(),v.end()
#define Pr(type) priority_queue<type>
#define gPr(type) priority_queue<type,vector<type>,greater<type>>
#define V(type) vector<type>
#define rep(i,n) for(int i=0; i<n; i++)
#define rer(i,st,en) for(int i=st; i<en; i++)
#define eb emplace_back
#define pri1(a) cout<<a<<'\n'
#define pri2(a,n) rep(i,n)cout<<a[i]<<'\n'
#define pri3(a,n) rep(i,n-1)cout<<a[i]<<' '; cout<<a[n-1]<<'\n'
#define prip(p) cout<<p.first<<' '<<p.second<<'\n'
template<class T> inline bool cmax(T& a, T b) { if (a < b) { a = b; return 1; } return 0; }
template<class T> inline bool cmin(T& a, T b) { if (a > b) { a = b; return 1; } return 0; }

int n,m,_,x,y,q;
string s,sb;
bool ok;
bool see[410][410]={};
string g[410];
Int b,w;
int d[]={1,0,-1,0,1};

int main(){
  cin.tie(nullptr); ios::sync_with_stdio(false);

  double x1,y1,x2,y2,x3,y3; double r,s,a,b,c;
  cin>>x1>>y1>>x2>>y2>>x3>>y3;
  a=sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)),b=sqrt((x3-x2)*(x3-x2)+(y2-y3)*(y2-y3)),c=sqrt((x1-x3)*(x1-x3)+(y1-y3)*(y1-y3));
  s=(a+b+c)/2;
  r=2*sqrt((s-a)*(s-b)*(s-c)*s)/(a+b+c);
  a=max({a,b,c});
  cout<<setprecision(10)<<r*a/(2*r+a)<<'\n';
}