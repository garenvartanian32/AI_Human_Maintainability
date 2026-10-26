#include <cstdio>
#include <cmath>
#include <cstring>
#include <ctime>
#include <climits>
#include <iostream>
#include <algorithm>
#include <set>
#include <map>
#include <queue>
#include <vector>
#include <sstream>
#include <typeinfo>
#include <fstream>

#define DIV 1000000007

using namespace std;

long long N, A;


long long calpow(long long a, long long b){
	if(a == 0){
		return 0;
	}
	long long ret = 1;
	for(int i = 0; i < b; i++){
		if(ret > N/a){
			return N + 1;
		}
		ret *= a;
	}
	return ret;
}

//num: eat times
long long cal(int num){
	num++;
	long long s = -1;
	long long e = LONG_MAX/3;

	while(s+1 < e){
		long long m = (s+e)/2;
		if(N <= calpow(m, num)){
			e = m;
		}else{
			s = m;
		}
	}

	//e is ok
	//how many e - 1
	long long rep = 0;
	for(int i = num; i >= 0; i--){
		if(pow(e, num - i) * pow(e-1, i) >= N){
			rep = i;
			break;
		}
	}

	return rep * (e-1) + (num - rep) * e;

}


int main(){
	cin >> N >> A;
	long long ans = LONG_MAX/3;
	for(int i = 0; pow(2, i) <= N; i++){
		ans = min(ans, i*A + cal(i));
	}
	cout << ans << endl;
}
