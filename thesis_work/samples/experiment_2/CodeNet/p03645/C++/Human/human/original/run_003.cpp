#include<bits/stdc++.h>
using namespace std;
const int mxn=1e6;
vector <int> adj[mxn];
int visited[mxn];
long long dist[mxn];
queue <int> q;
void bfs(int start)
{
    q.push(start);
    visited[start]=1;
    dist[start]=0;
    while(!q.empty())
    {
        int u=q.front();
        q.pop();
       // cout<<u<<' ';
        for(int i=0;i<adj[u].size();i++)
        {
           int v= adj[u][i];
           if(!visited[v])
           {
               visited[v]=1;
               q.push(v);
               dist[v]=1+dist[u];
           }
        }
    }
}
int main()
{
    int n,m;
    cin>>n>>m;
    int u,v;
    for(int i=0;i<m;i++)
    {
        cin>>u>>v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
   /*for(auto i:visited)
   {
       cout<<i<<" ";
   }*/

    bfs(1);

        if (dist[n]==2) cout<<"POSSIBLE"<<endl;
        else cout<<"IMPOSSIBLE"<<endl;
}
