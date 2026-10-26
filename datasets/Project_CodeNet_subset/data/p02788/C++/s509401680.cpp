#include <bits/stdc++.h>
using namespace std;

struct fenwick_tree{
	vector<long long> bit;
	int n;
	fenwick_tree(int na){
		n = na;
		bit.assign(n, 0);
	}
	long long query(int end){
		long long val = 0;
		for(; end >= 0; end = (end & (end + 1)) - 1) val += bit[end];
		return val;
	}
	void update(int idx, long long val){
		for(; idx < n; idx = idx | (idx + 1)) bit[idx] += val;
	}
};

int n;
long long d, a;
pair<long long, long long> arr[200001];

int main(){
	cin >> n >> d >> a;
	fenwick_tree bit(n + 1);
	for(int i = 1; i <= n; i++) cin >> arr[i].first >> arr[i].second;
	sort(arr, arr + n + 1);
	long long cnt = 0;
	for(int i = 1; i <= n; i++){
		arr[i].second = max(0LL, arr[i].second + bit.query(i));
		long long up = arr[i].first + 2 * d;
		auto it = upper_bound(arr, arr + n + 1, make_pair(up, LLONG_MAX));
		long long rem = arr[i].second / a;
		if(arr[i].second % a) rem++;
		bit.update(i, -rem * a);
		if(it != arr) bit.update(it - arr, rem * a);
		cnt += rem;
	}
	cout << cnt << "\n";
	return 0;
}
