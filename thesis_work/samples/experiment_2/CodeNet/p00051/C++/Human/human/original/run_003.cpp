#include <iostream>
#include <cstdio>
#include <string>
#include <algorithm>
#include <functional>
using namespace std;
#define rep2(x,from,to) for(int x = (from); x < (to); ++(x))
#define rep(x,to) rep2(x,0,to)

int main() {
	int n;
	cin >> n;
	while(n--) {
		char s[10];
		cin >> s;
		sort(s,s+8);
		int min = atoi(s);
		sort(s,s+8,greater<int>());	
		int max = atoi(s);
		cout << max - min << endl;
	}
	return 0;
}