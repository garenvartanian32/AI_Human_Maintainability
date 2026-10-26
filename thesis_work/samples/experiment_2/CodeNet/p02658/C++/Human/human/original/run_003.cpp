/*
 *  Created on: 31 / 5 / 2020
 *  Author:     Eslam Waheed
 */
#include <bits/stdc++.h>
//#include <bits/BIGINT.h>
//#include <bits/Int128.h>
using namespace std;

//#define _CRT_SECURE_NO_WARNINGS
//#define __USE_MINGW_ANSI_STDIO
#define Eslam__Waheed ios_base::sync_with_stdio(false);cout.tie(NULL);cin.tie(NULL);

#define ll   long long
#define ull  unsigned ll
#define dd   double
#define ld   long dd
#define ss   string

#define pb      push_back
#define all(c)  c.begin(), c.end()
#define rall(c) c.rbegin(), c.rend()
#define stp(n)  cout << fixed << setprecision(n);
#define iq(v)   v.resize(unique(v.begin(),v.end())-v.begin())
#define sz(s)   (ll)(s.size())
#define endl    '\n'

#define loop(i,m,n)  for(ll i=m;i<n;i++)
#define test(t)      while(t--)

//ll gcd(ll a, ll b) { return !b ? abs(a) : gcd(b, a % b); }
//ll lcm(ll a, ll b) { return abs(a / gcd(a, b)) * b; }

//const ld PI = acos(-1);
//const ll mod = 1e15 + 7;
//__int128
/*
 you can ^_^ (•‿•)

( إن الله وملائكته يصلون على النبي يا أيها الذين آمنوا صلوا عليه وسلموا تسليما )
*/
int main()
{
    //stp(0)
    Eslam__Waheed
    ll n; cin >> n;
    vector<ll>v(n);
    for (auto& it : v)
    {
        cin >> it;
    }
    sort(all(v));
    ll ans = 1;
    for (auto a : v)
    {
        if (a == 0)
        {
            cout << 0;
            return 0;
        }

        if (a > (ll)1e18 / ans)
        {
            cout << "-1\n";
            return 0;
        }
        ans *= a;
        if (ans > (ll)1e18)
        {
            cout << -1;
            return 0;
        }
    }
    cout << ans;
}
   