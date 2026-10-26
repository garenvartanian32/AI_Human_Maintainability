#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;

struct Team {
	int id;
	int uni;
	int solved;
	int penalty;
	bool selected;

	bool operator<(const Team& another) const {
		if(solved != another.solved) {
			return (solved > another.solved);
		}
		if (penalty != another.penalty) {
			return (penalty < another.penalty);
		}
		return id < another.id;
	}
};

int selectedCounts[1001];

int main() {
	int n;
	while(true) {
		cin >> n;
		if (n == 0) {
			break;
		}

		vector<Team> teams(n);
		for (int i = 0; i < n; i++) {
			cin >> teams[i].id >> teams[i].uni >> teams[i].solved >> teams[i].penalty;
			teams[i].selected = false;
		}

		for (int i = 0; i < 1001; i++) {
			selectedCounts[i] = 0;
		}

		sort(teams.begin(), teams.end());

		int entireSelectedCount = 0;

		for (int i = 0; i < n; i++) {
			if(entireSelectedCount < 10 && selectedCounts[teams[i].uni] < 3
					|| entireSelectedCount < 20 && selectedCounts[teams[i].uni] < 2
					|| entireSelectedCount < 26 && selectedCounts[teams[i].uni] < 1) {
				selectedCounts[teams[i].uni]++;
				entireSelectedCount++;
				cout << teams[i].id << endl;
			}
		}

	}
	return 0;
}