#include<bits/stdc++.h>
#define r(i,n) for(int i=0;i<n;i++)
using namespace std;
string s[21];
int dx[]={1,1,1,0,0,-1,-1,-1};
int dy[]={1,0,-1,1,-1,1,0,-1};
int h,w;
vector<string>v;
set<string> se;
void dfs(int d,int y,int x,int p,string t,int p1,int p2){
  if(d&&y==p1&&x==p2)return;
  t+=s[y][x];
  if(d){
    if(!se.count(t))se.insert(t);
    else v.push_back(t);
  }
  x+=dx[p],y+=dy[p];
  if(x<0)x=w-1;
  if(y<0)y=h-1;
  if(x>=w)x=0;
  if(y>=h)y=0;
  dfs(d+1,y,x,p,t,p1,p2);
}
int main(){
  while(cin>>h>>w,h){
    se.clear();
    v.clear();
    r(i,h)cin>>s[i];
    r(i,h)r(j,w)r(k,8)dfs(0,i,j,k,"",i,j);
    sort(v.begin(),v.end());
    int p=0;
    r(i,v.size())p=max(p,(int)v[i].size());
    if(v.size())r(i,v.size()){
      if(v[i].size()==p){
        cout<<v[i]<<endl;
        break;
      }
    }
    else cout<<0<<endl;
  }
}