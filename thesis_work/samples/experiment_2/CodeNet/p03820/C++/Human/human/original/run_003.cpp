#include<bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/trie_policy.hpp>

#define pb push_back
#define mp make_pair
#define taskname "A"

using namespace std;
using namespace __gnu_pbds;

typedef long long ll;
typedef long double ld;
typedef pair<int,int> ii;
typedef tree <int,null_type,less<int>,rb_tree_tag,tree_order_statistics_node_update> ordered_set;

const int maxn = 1e5 + 5;
const int mod = 1e9 + 7;

int f[maxn];
void add(int & x , int y){
    x += y;
    if(x >= mod)x -= mod;
}
int n , k;

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    if(fopen(taskname".INP","r")){
		freopen(taskname".INP", "r",stdin);
		freopen(taskname".OUT", "w",stdout);
    }
    cin >> n >> k;
    f[0] = 1;
    for(int i = 1 ; i <= k ; ++i)for(int j = i ; j < n  ; ++j)add(f[j],f[j-1]);
    for(int i = 1 ; i < n - k ; ++i)add(f[n-1],f[n-1]);
    cout << f[n - 1];
}
