#include <bits/stdc++.h>
#include <unordered_set>
using namespace std;
#define int long long
#define pb push_back
#define ld long double
#define endl "\n";
#define deb1(x) cout << #x << ": " << x << endl
#define deb2(x, y) cout << #x << ": " << x << " | " << #y << ": " << y << endl
#define fast                      \
    ios_base::sync_with_stdio(0); \
    cin.tie(0);                   \
    cout.tie(0);
#define printclock cerr << "Time : " << 1000 * (ld)clock() / (ld)CLOCKS_PER_SEC << "ms\n";
#define vii vector<int>
#define pii pair<int, int>
#define prec(n) fixed << setprecision(n)
#define mp make_pair
#define all(a) a.begin(), a.end()
#define rep(i, a, n) for (int i = (a); i < (n); ++i)
#define MOD LLONG_MAX
#define INF 1000000007
inline void get(vector<int> &arr, int n)
{
    int a;
    for (int i = 0; i < n; i++)
    {
        cin >> a;
        arr.push_back(a);
    }
}
inline void print(vector<int> &arr, int n)
{
    for (int i = 0; i < n; i++)
        cout << arr[i] << " ";
    cout << endl;
}
/*----------------------------------TEMPLATE------------------------------------*/

int32_t main()
{
#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    //freopen("output.txt", "w", stdout);
#endif
    fast;
    int t;
    t = 1;
    while (t--)
    {
        int n,k;
        cin >> n >> k;
        vii v;
        get(v,n);
        //i-> the player who's choosing now wins/loses when i stones are left.
        vector<bool> dp(k+1,false);
        rep(i,0,k+1)
        {
            rep(j,0,n)
            {
                //even if picking any one of the elements from the array takes you to a losing state
                //the player's choosing now wins (BOTH PLAY OPTIMALLY);
                if(i >= v[j] && dp[i-v[j]]==false)
                {
                    dp[i] = true;
                }
            }
        }
        if(dp[k]==true)
        {
            cout << "First" << endl;
        }
        else
        {
            cout << "Second" << endl;
        }
    }
    printclock;
    return 0;
}