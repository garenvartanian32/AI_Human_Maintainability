#include <bits/stdc++.h>
#define FOR(i,n) for(int (i)=0;(i)<(int)(n);++(i))
#define FORUL(i,l,u) for(int (i)=(int)(l);(i)<(int)(u);++(i))
#define FORULE(i,l,u) for(int (i)=(int)(l);(i)<=(int)(u);++(i))
#define FOREACH(it, X) for(__typeof((X).begin()) it = (X).begin(); it != (X).end(); it++)
#if defined(_MSC_VER) || __cplusplus > 199711L
#define AUTOVAR(newvar,v) auto newvar = (v)
#else
#define AUTOVAR(newvar,v) __typeof(newvar) r = (v)
#endif
#define PB(x) push_back(x)
#define MP(x,y) make_pair((x),(y))
#define MEMSET(m,v) memset(m,v,sizeof(m))

typedef long long				 ll;
typedef unsigned long long		ull;

#define DEBUG 1

#if DEBUG
#define DOUT cout<<"["<<__LINE__<<"]:"
#else
#define DOUT 0 && cout
#endif

using namespace std;
int A, B;

int main(int argc, char *argv[])
{
	ios::sync_with_stdio(false);

	cin >> A >> B;

	int ans = (B-1)/(A-1);
	if ((B-1)%(A-1)!=0) {
		ans += 1;
	}

	cout << ans << endl;

	return 0;
}


