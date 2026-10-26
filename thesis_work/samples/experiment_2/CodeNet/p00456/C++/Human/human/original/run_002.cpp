#include <iostream>
#include <algorithm>
#include <array>
#include <functional>
#include <map>
#include <math.h>
#include <queue>
#include <set>
#include <stdlib.h>
#include <string>
#include <vector>

#define INF 1000000000
#define MOD 1000000007
#define ll long long
#define rep(i,a,b) for(int i = (a); i < (b); i++)
#define bitget(a,b) (((a) >> (b)) & 1)
#define vint vector<int>
#define vvint vector<vector<int>>
#define vsort(x) sort(x.begin(),x.end())

using int64 = ll;

using namespace std;
int amin(int* array, int length) {
	int ret = INF;
	rep(i, 0, length)
		ret = min(ret, array[i]);
	return ret;
}
template<typename T>
void acin(T* array, int length){
	rep(i, 0, length){
		cin >> array[i];
	}
}
int main() {
	vint w(10), k(10);
	rep(i, 0, 10)
		cin >> w[i];
	rep(i, 0, 10)
		cin >> k[i];
	vsort(w);
	vsort(k);
	cout << w[9] + w[8] + w[7] << " " << k[9] + k[8] + k[7] << endl;

	return 0;
}