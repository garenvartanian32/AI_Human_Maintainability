#include <bits/stdc++.h>
using namespace std;
#define rep(i, n) for (int i = 0; i < (int)(n); i++)

typedef long long ll;
typedef pair<int, int> P;

const ll mod =  1000000007;
const int inf = 1e9;
const long long INF = 1LL << 60;   // INFの値は1152921504606846976

int main()
{
    int n,k;
    cin >> n >> k;
    int l = 0;
    int r = inf;
    rep(i,n){
        int a,b;
        cin >> a >> b;
        l = max(l,a);
        r = min(r,b);
    }
    if(l <= r){
        cout << r - l + 1 << endl;
    }else{
        cout << 0 << endl;
    }
}
