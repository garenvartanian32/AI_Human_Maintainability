#include<iostream>
#include<algorithm>
#include<stack>
#include<queue>
#include<string>
#include<string.h>
#include<set>
#include<functional>
using namespace std;

#define INF 1<<21
#define MOD 1000000007

int main() {
  int n;
  cin >> n;
  for (int i = 0; i < n; i++) {
	int dx, dy;
	int x = 0, y = 0;
	int dis = 0;
	int ansx, ansy;
	while (1) {
	  cin >> dx >> dy;
	  if (dx == 0 && dy == 0)break;
	  x += dx; y += dy;
	  if (dis == x*x + y*y && x > ansx) {
		ansx = x;
		ansy = y;
	  }
	  else if (dis < x*x + y*y) {
		dis = x*x + y*y;
		ansx = x;
		ansy = y;
	  }
	}
	cout << ansx << " " << ansy << endl;
  }
  return 0;
}