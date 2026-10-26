#include <vector>
#include <list>
#include <set>
#include <map>
#include <stack>
#include <queue>
#include <string>
#include <algorithm>
#include <iostream>
#define rep(i, n) for (int i = 0; i < (int)(n); i++)
#define forep(i, a, b) for(int64_t i=(a);i<(b);++i)
#define all(obj) (obj).begin(), (obj).end()
#define in(x) cin >> (x)
#define out(y) cout << (y) << "\n"
#define i64 int64_t
#define str string
using namespace std;
void Main() {
	/*----------------------------------------------------------------------------------------------------*/
	i64 A, V, B, W, T;
	in(A); in(V); in(B); in(W); in(T);

	i64 dir = 0;
	if (A > B) dir = -1;
	else dir =1;

	V *= dir;
	W *= dir;

	for (i64 i = 0; i < T; ++i)
	{
		A += V;
		B += W;
		if ((dir == 1 && A >= B) || (dir == -1 && A <= B)) {
			out("YES");
			return;
		}
	}

	out("NO");

	/*----------------------------------------------------------------------------------------------------*/
}
int main() {
	cin.tie(nullptr);
	ios_base::sync_with_stdio(false);
	Main();
	return 0;
}