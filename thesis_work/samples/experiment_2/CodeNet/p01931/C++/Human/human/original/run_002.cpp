#include "bits/stdc++.h"
using namespace std;
typedef long long ll;
typedef pair<int,int> pii;
#define rep(i,n) for(ll i=0;i<(ll)(n);i++)
#define all(a) (a).begin(),(a).end()
#define pb emplace_back
#define INF (1e9+1)

int main(){
    int n;
    string s;
    cin>>n>>s;
    rep(i,s.size()-1){
        if(s[i]=='x' && s[i+1]=='x'){
            cout<<i+1<<endl;
            return 0;
        }
    }
    cout<<n<<endl;
}