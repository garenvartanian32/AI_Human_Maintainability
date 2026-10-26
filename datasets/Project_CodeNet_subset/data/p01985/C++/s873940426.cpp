#include <bits/stdc++.h>
#define r(i,n) for(int i=0;i<n;i++)
using namespace std;
int n,m,a,b,l,r,f,u[1111];
vector<int>v[1111];
void dfs(int x,int d){
    u[x]=d;
    if(d)l++;
    else r++;
    r(i,v[x].size()){
        int nx=v[x][i];
        if(u[nx]!=-1){
            if(u[nx]==u[x])f++;
        }
        else{
            dfs(nx,d^1);
        }
    }
}
int main(){
    while(cin>>n>>m,n){
        l=r=f=0;
        r(i,n)v[i].clear();
        r(i,m){
            cin>>a>>b;
            a--;b--;
            v[a].push_back(b);
            v[b].push_back(a);
        }
        memset(u,-1,sizeof(u));
        dfs(0,0);
        if(f)cout<<0<<endl;
        else{
            vector<int>ans;
            if(l%2==0)ans.push_back(l/2);
            if(r%2==0&&l!=r)ans.push_back(r/2);
            sort(ans.begin(),ans.end());
            cout<<ans.size()<<endl;
            r(i,ans.size()){
                cout<<ans[i]<<endl;
            }
        }
    }
}

