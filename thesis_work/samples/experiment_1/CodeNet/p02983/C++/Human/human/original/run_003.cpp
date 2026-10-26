#include<bits/stdc++.h>
#include<bits/stdc++.h>
using namespace std;
//Optimisations
#pragma GCC target ("avx2")
#pragma GCC optimization ("unroll-loops")
#pragma GCC optimize("O2")
//shortcuts for functions
#define pb push_back
#define mp make_pair
#define ff first 
#define ss second
#define endl "\n"
#define all(v) v.begin(),v.end()
#define rall(v) v.rbegin(),v.rend()
#define th(n) cout<<n<<endl
#define gc getchar_unlocked
#define ms(s, n) memset(s, n, sizeof(s))
#define prec(n) fixed<<setprecision(n)
#define n_l '\n'
// make it python 
#define gcd __gcd
#define append push_back
#define str to_string
#define upper(s) transform(s.begin(),s.end(),s.begin(),::toupper)
#define lower(s) transform(s.begin(),s.end(),s.begin(),::tolower)
#define print(arr) for(auto el: arr) cout<<el<<" ";cout<<endl
// utility functions shortcuts
#define max3(a,b,c) max(a,max(b,c))
#define min3(a,b,c) min(a,min(b,c))
#define sswap(a,b) {a=a^b;b=a^b;a=a^b;}
#define swap(a,b) {auto temp=a; a=b; b=temp;}
#define init(dp) memset(dp,-1,sizeof(dp));
#define set0(dp) memset(dp,0,sizeof(dp));
#define bits(x) __builtin_popcount(x)
#define SORT(v) sort(all(v))
#define endl "\n"
#define forr(i,n) for(ll i=0;i<n;i++)
#define formatrix(i,n) for(ll i=0;i<n;i++, cout<<"\n")
#define eof (scanf("%d" ,&n))!=EOF
// declaration shortcuts
#define vi vector<int>
#define vll vector<ll>
#define vvi vector<vector<int>>
#define vvl vector<vector<ll>> 
#define pll pair<ll,ll>
#define ppl pair<ll,pp>
#define ull unsigned long long
#define ll long long
#define mll map< ll, ll > 
#define sll set< ll > 
#define uni(v) 	v.erase(unique(v.begin(),v.end()),v.end());
#define ini(a, v)       memset(a, v, sizeof(a))
using namespace std;
int main(){
    
    ll n,m,ma=1e18;
    cin>>n>>m;
    if((n/2019)!=(m/2019))
     cout<<0;
     else{
         
         for(int i=n;i<m;i++)
         {
             for(int j=i+1;j<=m;j++)
             {
                 ma=min(ma,(ll)((i%2019)*(j%2019))%2019);
             }
             
         }
         cout<<ma;
     }
    return 0;
}