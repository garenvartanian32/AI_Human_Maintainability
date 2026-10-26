#include <bits/stdc++.h>
#define int long long
#define IOS std::ios::sync_with_stdio(false); cin.tie(NULL);cout.tie(NULL);
#define pb push_back
#define mod 1000000007
#define lld long double
#define mii map<int, int>
#define mci map<char, int>
#define msi map<string, int>
#define pii pair<int, int>
#define ff first
#define ss second
#define all(x) (x).begin(), (x).end()
#define rep(i,x,y) for(int i=x; i<y; i++)
#define fill(a,b) memset(a, b, sizeof(a))
#define vi vector<int>
#define setbits(x) __builtin_popcountll(x)
using namespace std;
const long long N=200005, INF=2000000000000000000;
int power(int a, int b, int p)
    {
        if(a==0)
        return 0;
        int res=1;
        a%=p;
        while(b>0)
        {
            if(b&1)
            res=(res*a)%p;
            b>>=1;
            a=(a*a)%p;
        }
        return res;
    }

int32_t main()
{
    IOS;
    int x,y,a,b,c,total,ans=0;
    vector <int> v;
    cin>>x>>y>>a>>b>>c;
    int r[a],g[b],n[c];
    for(int i=0;i<a;i++)
    cin>>r[i];
    for(int i=0;i<b;i++)
    cin>>g[i];
    for(int i=0;i<c;i++)
    cin>>n[i];
    sort(r, r+a);
    sort(g, g+b);
    sort(n, n+c);
    for(int i=a-1;i>=a-(min(x, a));i--)
    v.pb(r[i]);
    for(int i=b-1;i>=b-(min(y, b));i--)
    v.pb(g[i]);
    for(int i=c-1;i>=0;i--)
    v.pb(n[i]);
    int l=v.size(),blah=0;
    sort(v.begin(), v.end());
    for(int i=l-1;i>=0;i--)
    {
        ans+=v[i];
        blah++;
        if(blah==x+y)
        break;
    }
    cout<<ans;
}