#define	_USE_MATH_DEFINES
#include <iostream>
#include <iomanip>
#include <algorithm>
#include <functional>
#include <vector>
#include <cstdio>
#include <cstring>
#include <cmath>
#include <cfloat>
#include <map>
#include <queue>
#include <stack>
#include <list>
#include <string>
#include <set>
#include <complex>
#include <utility>
#include <numeric>
#define  rep(i,n) for(int i=0;i<(n);i++)
#define  REP(i,a,n) for(int i=a;i<(n);i++)
#define  rrep(i,n) for(int i=(n)-1;i>=0;i--)
#define  VI	vector<int>
#define	 VS vector<string>
#define  all(a) (a).begin(),(a).end()
#define  debug(x) cout<<#x<<": "<<x<<endl
using namespace std;
typedef pair<int,int> P;
typedef long long ll;

const int INF=1000000;
char fi[101][101];
int d[21][21];
int day[12]={31,28,31,30,31,30,31,31,30,31,30,31};
int N,M;
int sx,sy;
int gx,gy;
int w,h;
int ans;

int dx[4]={0,0,-1,1};
int dy[4]={-1,1,0,0};
const int MAX_V=100;
const int MAX_N=100;
char o[3]={'+','-','*'};
#define md 1000003

int dp[353][353]={0};
int bow[353][353]={0};


int main(){
	
	int n;
	cin>>n;
	while(n--){
		int x=0,y=0;
		int a,b;
		int xa=0,ya=0;
		double ans=0;
		while(cin>>a>>b,a!=0||b!=0){
			x+=a;
			y+=b;
			if(ans<sqrt(x*x+y*y)){
				xa=x;
				ya=y;
				ans=sqrt(x*x+y*y);
			}
			else if(ans==sqrt(x*x+y*y)){
				if(x>xa){
					xa=x;
					ya=y;
				}
			}
				
		}
		cout<<xa<<" "<<ya<<endl;
	}
	
	
	

	
}