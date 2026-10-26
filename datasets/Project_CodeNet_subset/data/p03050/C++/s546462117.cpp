#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int main(){

	ll N;
	cin >> N;

	ll ans=0;


	if(N==1 || N==2){
		ans = 0;
	}
	else{
		for(ll i=2; i<N; i++){
			if((N-i) % i == 0){
				ll t = (N-i) / i;
				if(t <= i) break;
				else {
	                  ans += t;

                }
			} 
			else{
				if(i >= (N-i)/i) break;
			}
		}
      	ans += (N-1);
	}

	cout << ans << endl;
	return 0;
}