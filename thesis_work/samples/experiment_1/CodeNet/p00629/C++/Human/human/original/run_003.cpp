#include <algorithm>
#include <cstdlib>
#include <cstring>
#include <iostream>
#include <vector>
using namespace std;

constexpr int MAX = 1001;
int cnt[MAX];

struct team {
	int id, univ, AC, time;
	team(int id_, int univ_, int AC_, int time_):id(id_), univ(univ_), AC(AC_), time(time_) {}
	bool operator>(const team &t) const {
		if(AC != t.AC) return AC > t.AC;
		if(time != t.time) return time < t.time;
		return id < t.id;
	}
};

int main() {
	cin.tie(nullptr);
	ios::sync_with_stdio(false);

	for(int n; cin >> n && n;) {
		vector<team> teams;
		teams.reserve(n);

		for(int i = 0; i < n; ++i) {
			int id, u, a, p;
			cin >> id >> u >> a >> p;
			teams.emplace_back(id, u, a, p);
		}

		sort(teams.begin(), teams.end(), greater<team>());
		
		int sum = 0;
		memset(cnt, 0, sizeof(cnt));
		int limit = 3;
		for(const auto &t : teams) {
			if(cnt[t.univ] < limit) {
				cout << t.id << endl;
				++cnt[t.univ];
				++sum;
				if(sum == 10 || sum == 20 || sum == 26) --limit;
			}
		}
	}

	return EXIT_SUCCESS;
}