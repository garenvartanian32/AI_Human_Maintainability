#include <bits/stdc++.h>
typedef long long ll;
using namespace std;
#define FOR(i,a,b) for(int i=(a);i<(b);i++)
#define REP(i,a) FOR(i,0,a)
const ll INF=1e15;
const int MAX_N=500,MAX_C=30;

int N,C,D[MAX_C][MAX_C];
int cnt[3][MAX_C];

int main(){
	cin>>N>>C;
	REP(i,C){
		REP(j,C){
			cin>>D[i][j];
		}
	}
	REP(i,N){
		REP(j,N){
			int c;
			cin>>c;
			c--;
			cnt[(i+j)%3][c]++;
		}
	}
	ll ans=INF;
	REP(i,C){
		REP(j,C){
			REP(k,C){
				if (i!=j && j!=k && k!=i){
					vector<int> vec={i,j,k};
					ll s=0;
					REP(p,3){
						REP(c,C){
							s+=D[c][vec[p]]*cnt[p][c];
						}
					}
					ans=min(ans,s);
				}
			}
		}
	}
	cout<<ans<<endl;
	return 0;
}