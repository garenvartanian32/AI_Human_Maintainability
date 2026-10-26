#include "bits/stdc++.h"
#define in std::cin
#define out std::cout
#define rep(i,N) for(int i=0;i<N;++i)
typedef long long int LL;

LL N;
std::vector<LL>G, cnt;
std::set<LL>list;
void dfs(LL v)
{
	if (cnt[v] >= 2 || cnt[v] == -1) return;
	++cnt[v];
	list.insert(v);
	dfs(G[v]);
}

int main()
{
	in >> N;
	std::vector<LL>a(N);
	a.resize(N);
	rep(i, N) in >> a[i];

	G.resize(N + 1); cnt.resize(N + 1);
	rep(i, N) G[i + 1] = (i + a[i]) % N + 1;

	LL ans = 0;
	rep(i, N)
	{
		if (cnt[i + 1] == 0)
		{
			list.clear();
			dfs(i + 1);
			for (auto j : list)
			{
				if (cnt[j] < 2) cnt[j] = -1;
			}
		}
	}
	rep(i, N)
	{
		if (cnt[i + 1] >= 2) ++ans;
	}

	out << ans << std::endl;
}

