#include <iostream>
#include <fstream>
#include <sstream>
#include <iomanip>
#include <complex>
#include <string>
#include <vector>
#include <list>
#include <deque>
#include <stack>
#include <queue>
#include <set>
#include <map>
#include <bitset>
#include <functional>
#include <utility>
#include <algorithm>
#include <numeric>
#include <typeinfo>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <climits>
#include <ctime>

using namespace std;

#define dump(n) cout<<"# "<<#n<<"="<<(n)<<endl
#define debug(n) cout<<__FILE__<<","<<__LINE__<<": #"<<#n<<"="<<(n)<<endl
#define repi(i,a,b) for(int i=int(a);i<int(b);i++)
#define rep(i,n) repi(i,0,n)
#define iter(c) __typeof((c).begin())
#define foreach(i,c) for(iter(c) i=(c).begin();i!=(c).end();i++)
#define allof(c) (c).begin(),(c).end()
#define mp make_pair

typedef unsigned int uint;
typedef long long ll;
typedef unsigned long long ull;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef vector<string> vs;
typedef pair<int,int> pii;

double getcog(vi& ps,vi& ws)
{
	int size=ps.size();
	int temp=0;
	rep(i,size)
		temp+=(i+1)*ws[ps[i]];
	return (double)temp/accumulate(allof(ws),0);
}

int main()
{
	for(int n;cin>>n,n;){
		vs ns(n);
		vi ws(n),cs(n);
		rep(i,n)
			cin>>ns[i]>>ws[i]>>cs[i];
		
		double cog=1e10;
		vi ps(n),res(n);
		vi ss(n);
		rep(i,n) ps[i]=i;
		do{
			for(int i=n-2;i>=0;i--)
				ss[i]=ss[i+1]+ws[ps[i+1]];
			bool ok=true;
			rep(i,n)
				ok&=ss[i]<=cs[ps[i]];
			if(ok){
				double temp=getcog(ps,ws);
				if(temp<cog){
					cog=temp;
					res=ps;
				}
			}
		}while(next_permutation(allof(ps)));
		
		rep(i,n)
			cout<<ns[res[i]]<<endl;
	}
	
	return 0;
}