#include <bits/stdc++.h>
using namespace std;

int main(){
	int n,m;
	cin>>n>>m;
	vector<pair<int,int>> ab(n);
	for (int i = 0; i < n; i++) {
		cin>>ab[i].first>>ab[i].second;
	}
	sort(
		ab.begin(),
		ab.end(),
		[](const pair<int,int> x,const pair<int,int> y){return x.first<y.first;}
	);
	long long int ans=0;
	int num=0;
	for (int i = 0; i < n; i++) {
		if (num+ab[i].second<=m) {
			ans+=(long long int)ab[i].first*ab[i].second;
			num+=ab[i].second;
		}else {
			ans+=(long long int)(m-num)*ab[i].first;
			break;
		}
	}
	cout<<ans<<endl;
}