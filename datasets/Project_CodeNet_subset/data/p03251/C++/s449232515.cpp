#include<cstdio>
#include<algorithm>
#include<iostream>
using namespace std;
int main() {
	int n, m, x, y, maxx, miny;
	cin >> n >> m >> x >> y;
	maxx = x, miny = y;
	for (int i = 1; i <= n; i++) {
		int x;
		cin >> x;
		maxx = max(maxx, x);
	}
	for (int i = 1; i <= m; i++) {
		int x;
		cin >> x;
		miny = min(miny, x);
	}
	if (maxx < miny) cout << "No War" << endl;
	else cout << "War" << endl;
	//getchar(); getchar(); getchar();
	return 0;
}