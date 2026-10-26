#include<iostream>
#include<string>
#include<vector>
#include<algorithm>
#define ll long long
#define rep(i,n) for (int i=0; i<(int)(n); i++)
using namespace std;
int main() {
	int n, m;
	cin >> n >> m;
	int L[100000];
	int R[100000];
	int mM = 1;
	int Mm = n;
	rep(i, m) {
		cin >> L[i] >> R[i];
		mM = max(mM, L[i]);
		Mm = min(Mm, R[i]);
	}
	if (Mm < mM) {
		cout << "0" << endl;
	}
	else {
		cout << Mm - mM + 1 << endl;
	}
	return 0;
}