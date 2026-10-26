//              +-- -- --++-- +-In the name of ALLAH-+ --++-- -- --+              \\

#include <bits/stdc++.h>

#define F first
#define S second
#define _sz(x) (int)x.size()

using namespace std ;
using ll = long long ;
using ld = long double ;
using pii = pair <int , int> ;

const int N = 1e5 + 20 ;
int n , a , b , h[N] ;

int main(){
	ios::sync_with_stdio(false) , cin.tie(0) , cout.tie(0) ;

	cin >> n >> a >> b ;

	a -= b ;

	for (int i = 0 ; i < n ; i ++) cin >> h[i] ;

	int low = -1 , high = 1e9 + 20 ;

	while (high - low > 1) {
		int mid = (low + high) / 2 ;
	
		ll sum = 0 ;

		for (int i = 0 ; i < n ; i ++) {
			int v = max(0ll , h[i] - 1ll * b * mid) ;

			sum += (v + a - 1) / a ;
		}

		if (sum <= mid) high = mid ;
		else low = mid ;
	}

	cout << high << '\n' ;
}
