// #define _GLIBCXX_DEBUG // for STL debug (optional)
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <string>
#include <cstring>
#include <deque>
#include <list>
#include <queue>
#include <stack>
#include <vector>
#include <utility>
#include <algorithm>
#include <map>
#include <set>
#include <complex>
#include <cmath>
#include <limits>
#include <cfloat>
#include <climits>
#include <ctime>
#include <cassert>
#include <numeric>
#include <fstream>
#include <functional>
#include <bitset>
using namespace std;

#define debug(...) fprintf(stderr, __VA_ARGS__)
#define int long long int
 
template<typename T> void chmax(T &a, T b) {a = max(a, b);}
template<typename T> void chmin(T &a, T b) {a = min(a, b);}
template<typename T> void chadd(T &a, T b) {a = a + b;}
 
typedef pair<int, int> pii;
typedef long long ll;
 
int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};
const ll INF = 1001001001001001LL;
const ll MOD = 1000000007LL;

bool is_leapyear(int y) {
    if(y % 400 == 0) return true;
    if(y % 100 == 0) return false;
    return y % 4 == 0;
}

signed main() {
    int A, B;
    int line_cnt = 0;
    while(cin >> A >> B, A || B) {
        if(line_cnt > 0) cout << endl;
        line_cnt++;
        
        int cnt = 0;
        for(int i=A; i<=B; i++) {
            if(is_leapyear(i)) {
                cout << i << endl;
                cnt++;
            }
        }

        if(cnt == 0) cout << "NA" << endl;
    }
    return 0;
}

