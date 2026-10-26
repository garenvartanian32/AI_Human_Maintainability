#include <queue>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>
using namespace std;
#define N 2050
#define M 100005
#define ll long long
#define inf 0x3f3f3f3f
int n, m;
int x1[N], y1[N], c1[N];
int x2[N], y2[N], c2[N];
int fir[N], nex[M], tar[M], cap[M], len[M], cnt = 1;
int vis[N], d[N], dx[N];
ll dis[N];
void add(int a, int b, int c, int d)
{
	++cnt;
	tar[cnt] = b;
	cap[cnt] = c;
	len[cnt] = d;
	nex[cnt] = fir[a];
	fir[a] = cnt;
}
void Add(int a, int b, int c, int d)
{
	add(a, b, c, d);
	add(b, a, 0, -d);
}
int aug(int s, int t, int augco)
{
	if (s == t)
		return augco;
	int augc = augco, delta;
	vis[s] = 1;
	for (int i = fir[s]; i; i = nex[i])
	{
		int v = tar[i];
		if (cap[i] && dis[s] == dis[v] + len[i] && !vis[v])
		{
			delta = aug(v, t, min(augc, cap[i]));
			cap[i] -= delta;
			cap[i ^ 1] += delta;
			augc -= delta;
			if (augc == 0)
				return augco;
		}
	}
	return augco - augc;
}
void SPFA(int s)
{
	memset(dis, 0x3f, sizeof(dis));
	dis[s] = 0;
	vis[s] = 1;
	queue<int>q;
	q.push(s);
	while (!q.empty())
	{
		int x = q.front();
		q.pop();
		for (int i = fir[x]; i; i = nex[i])
		{
			int v = tar[i];
			if (cap[i ^ 1])
			{
				if (dis[v] > dis[x] + len[i ^ 1])
				{
					dis[v] = dis[x] + len[i ^ 1];
					if (!vis[v])
						vis[v] = 1, q.push(v);
				}
			}
		}
		vis[x] = 0;
	}
}
ll sap(int s, int t, int g)
{
	int flow = 0;
	ll cost = 0;
	SPFA(t);
	while (1)
	{
		int delta = 0;
		memset(vis, 0, sizeof(vis));
		while ((delta = aug(s, t, inf)))
		{
			flow += delta;
			cost += dis[s] * delta;
			memset(vis, 0, sizeof(vis));
		}
		ll c = 1ll << 60;
		for (int x = 1; x <= g; x++)
			if (vis[x])
				for (int i = fir[x]; i; i = nex[i])
					if (cap[i] && !vis[tar[i]])
						c = min(c, dis[tar[i]] + len[i] - dis[x]);
		for (int i = 1; i <= g; i++)
			if (vis[i])
				dis[i] += c;
		if (c == 1ll << 60)
			break;
	}
	return cost;
}
int main()
{
	scanf("%d", &n);
	for (int i = 1; i <= n; i++)
	{
		scanf("%d%d%d", &x1[i], &y1[i], &c1[i]);
		Add(1, i + 6, c1[i], 0);
	}
	for (int i = 1; i <= n; i++)
	{
		scanf("%d%d%d", &x2[i], &y2[i], &c2[i]);
		Add(n + i + 6, 2, c2[i], 0);
	}
	for (int i = 1; i <= n; i++)
	{
		Add(i + 6, 3, inf, -x1[i] -y1[i]);
		Add(i + 6, 4, inf, -x1[i] +y1[i]);
		Add(i + 6, 5, inf, +x1[i] -y1[i]);
		Add(i + 6, 6, inf, +x1[i] +y1[i]);
	}
	for (int i = 1; i <= n; i++)
	{
		Add(3, n + i + 6, inf, +x2[i] +y2[i]);
		Add(4, n + i + 6, inf, +x2[i] -y2[i]);
		Add(5, n + i + 6, inf, -x2[i] +y2[i]);
		Add(6, n + i + 6, inf, -x2[i] -y2[i]);
	}
	printf("%lld\n", -sap(1, 2, n * 2 + 6));
}