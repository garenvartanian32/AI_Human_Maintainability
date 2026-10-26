#include<iostream>
#include<cstdio>
#include<cmath>
#include<cstring>
#include<string>
#include<stack>
#include<queue>
#include<vector>
#include<algorithm>
#include<iomanip>
#include<utility>
typedef long long int ll;
#define FOR(i,a,b) for(int i=(a);i<(b);i++)
#define REP(i,n) for(int i=0;i<signed(n);i++)
#define EREP(i,n) for(int i=1;i<=signed(n);i++)
#define ALL(a) (a).begin(),(a).end()
using std::cout;
using std::vector;
using std::endl;
using std::cin;
using std::string;
//#define EVEL 1
#ifdef EVEL
#define DEB(X) cout << #X << ":" <<X<<" " ;
#define TF(f) f ? cout<<"true  " : cout<<"false ";
#define END cout<<"\n";
#else
#define DEB(X) {}
#define TF(f) {}
#define END {}
#endif
const int MOD = 1000000007;
const ll INF = 50000000000000000;
typedef std::pair<int,int> P;
struct edge {int to,cost;};
    ll N;
    std::string A,B;
    ll ans=0;

int main(){
    std::ios_base::sync_with_stdio(false);
    cin>>N>>A>>B;
    REP(i,N){
        if(A[i]==B[i]){
            if(i==0)ans=3;
            else{
                if(A[i-1]==B[i-1])ans=ans*2%MOD;
            }
        }else{
            if(i==0)ans=6;
            else{
                if(A[i-1]!=B[i-1])ans=ans*3%MOD;
                else ans=ans*2%MOD;
            }
            i++;
        }
    }
    cout<<ans%MOD;
    return 0;
}

//ARC 081 D - Coloring Dominoes
