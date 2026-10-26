#include<iostream>
#include<cstdio>
#include<string>
#include<algorithm>
using namespace std;

int main()
{
	int a;
	cin >> a;
	for (int b = 0; b < a; b++) {
		string c;
		cin >> c;
		int sum = 26, MAX = 0,MIN=1<<29;
		char train[52];
		for (int d = 0; d < c.length()/3+1; d++) {
			train[sum] = c[d*3];
			MAX = max(MAX, sum);
			MIN = min(MIN, sum);
			if (d<c.length()/3) {
				if (c[d*3+2] == '>')sum++;
				else sum--;
			}
		}
		for (int i = MIN; i <= MAX; i++) {
			cout << train[i];
		}
		cout << endl;
	}
}