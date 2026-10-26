#include <bits/stdc++.h>

using namespace std;

const long long MOD = 1000000007LL;
long long GCD(long long a, long long b){return b == 0 ? a : GCD(b, a % b);}

vector<long long> a, b;

int main(){

    cin.sync_with_stdio(0); cin.tie(0); cout.tie(0);

    //freopen("input.txt", "r", stdin);
    //freopen("output.txt", "w", stdout);

    int n;
    cin >> n;

    for(int i = 0; i < n; ++i){
        long long j;
        cin >> j;
        a.push_back(j);
    }

    for(int i = 0; i < n; ++i){
        long long j;
        cin >> j;
        b.push_back(j);
    }

    long long tot = 0;
    long long useful = 0;

    for(int i = 0; i < n; ++i){
        if(b[i] > a[i]){
            useful += (b[i] - a[i]) / 2;
        } else {
            tot += a[i] - b[i];
        }
    }

    if(tot > useful)
        cout << "No";
    else
        cout << "Yes";

    return 0;
}
