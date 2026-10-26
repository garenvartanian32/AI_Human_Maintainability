#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <set>
#include <map>
#include <string>
#include <stack>
#include <queue>
#include <cmath>
#include <cstdio>
#include <istream>
#include <sstream>
#include <iomanip>
#include <iterator>
#include <climits>
using namespace std;

typedef ostringstream OSS;
typedef istringstream ISS;

typedef vector<int> VI;
typedef vector< VI > VVI;

typedef long long LL;

typedef pair<int, int> PII;
typedef vector<PII> VPII;

#define X first
#define Y second

const char NONE = '0';

int main(void) {
	int N;
	cin >> N;
	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;

		string train(500, NONE);
		int pos = 250;
		train[pos] = str[0];

		for (int j = 1; j < (int)str.size(); j += 3) {
			char c = str[j + 2];
			int dir = str[j] == '<' ? -1 : 1;
			pos += dir;
			train[pos] = c;
		}

		remove_if(train.begin(), train.end(), [](char c){ return c == NONE; });
		train = train.substr(0, train.find(NONE));

		cout << train << endl;
	}

	return 0;
}