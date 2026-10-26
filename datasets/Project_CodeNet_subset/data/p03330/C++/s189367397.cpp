#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
#include <cmath>
#include <queue>
#include <map>
#include <unordered_map>
#include <set>
#include <functional>
#include <iomanip>
using namespace std;

typedef long long ll;

int main() {
	int N, C; cin >> N >> C;
	vector<vector<int>> D(C + 2, vector<int>(C + 2)), c(N + 2, vector<int>(N + 2));
	for (int i = 1; i <= C; i++) {
		for (int j = 1; j <= C; j++) {
			cin >> D[i][j];
		}
	}
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> c[i][j];
		}
	}
	vector<vector<int>> s(3, vector<int>(C + 2, 0));
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			for (int k = 1; k <= C; k++) {
				s[(i + j) % 3][k] += D[c[i][j]][k];
			}
		}
	}
	int ans = 1e9;
	for (int i = 1; i <= C; i++) {
		for (int j = 1; j <= C; j++) {
			for (int k = 1; k <= C; k++) {
				if (i == j || j == k || k == i) continue;
				ans = min(ans, s[0][i] + s[1][j] + s[2][k]);
			}
		}
	}
	cout << ans << endl;

	return 0;
}