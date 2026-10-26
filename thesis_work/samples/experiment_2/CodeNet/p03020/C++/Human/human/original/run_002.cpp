#include<cstdio>
#include<queue>
#include<cstring>
#include<algorithm>
using namespace std;
#define N 2050
struct edge{int t,next,v,c;}ed[N*30];
int n,a,b,c,head[N],ct,cnt,vis2[N],vis3[N],vis[N];
long long dis[N],as;
void adde(int f,int t,int w,int c){ed[++cnt]=(edge){t,head[f],w,c};head[f]=cnt;ed[++cnt]=(edge){f,head[t],0,-c};head[t]=cnt;}
void bfs2(int t)
{
	memset(vis2,0,sizeof(vis2));
	queue<int> tp;
	tp.push(t);
	vis2[t]=1;
	while(!tp.empty())
	{
		int x=tp.front();tp.pop();
		for(int i=head[x];i;i=ed[i].next)
		if(ed[i^1].v)
		if(!vis2[ed[i].t])vis2[ed[i].t]=1,tp.push(ed[i].t);
	}
}
bool spfa_is_dead(int s,int t)
{
	bfs2(t);
	memset(vis,0,sizeof(vis));
	memset(vis3,0,sizeof(vis3));
	memset(dis,0x3f,sizeof(dis));
	queue<int> tp;
	tp.push(s);
	dis[s]=0;vis[s]=1;
	while(!tp.empty())
	{
		int x=tp.front();tp.pop();
		vis[x]=0;
		for(int i=head[x];i;i=ed[i].next)
		if(ed[i].v&&vis2[ed[i].t])
		if(dis[ed[i].t]>dis[x]+ed[i].c)
		{
			dis[ed[i].t]=dis[x]+ed[i].c;
			if(!vis[ed[i].t])vis[ed[i].t]=1,tp.push(ed[i].t);
		}
	}
	return dis[t]<=1e15;
}
int dfs(int u,int t,int f)
{
	if(u==t||!f)return f;
	vis3[u]=1;
	int as1=0,tp;
	for(int i=head[u];i;i=ed[i].next)
	if(!vis3[ed[i].t]&&vis2[ed[i].t]&&ed[i].v&&dis[ed[i].t]==dis[u]+ed[i].c&&(tp=dfs(ed[i].t,t,min(ed[i].v,f))))
	{
		as1+=tp,f-=tp;
		ed[i].v-=tp,ed[i^1].v+=tp;
		as+=1ll*ed[i].c*tp;
		if(!f)return as1;
	}
	return as1;
}
long long dinic(int s,int t)
{
	while(spfa_is_dead(s,t))
	dfs(s,t,1e8);
	return as;
}
int main()
{
	scanf("%d",&n);
	ct=6;cnt=1;
	for(int i=1;i<=n;i++)
	{
		scanf("%d%d%d",&a,&b,&c);
		++ct;
		adde(1,ct,c,0);
		adde(ct,3,c,a+b);
		adde(ct,4,c,a-b);
		adde(ct,5,c,-a+b);
		adde(ct,6,c,-a-b);
	}
	for(int i=1;i<=n;i++)
	{
		scanf("%d%d%d",&a,&b,&c);
		++ct;
		adde(ct,2,c,0);
		adde(3,ct,c,-a-b);
		adde(4,ct,c,-a+b);
		adde(5,ct,c,a-b);
		adde(6,ct,c,a+b);
	}
	printf("%lld\n",-dinic(1,2));
}