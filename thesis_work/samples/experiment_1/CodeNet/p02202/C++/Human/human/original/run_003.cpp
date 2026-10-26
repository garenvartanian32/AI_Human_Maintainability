#include <iostream>
#include <vector>
#include <array>
#include <list>
#include <string>
#include <stack>
#include <queue>
#include <deque>
#include <unordered_map>
#include <unordered_set>
#include <tuple>
#include <memory>
#include <cmath>
#include <algorithm>
#include <functional>
#include <iomanip>
#include <numeric>
#include <climits>
#include <cfloat>
#include <cassert>
#include <random>


int main() {
	long long int n; std::cin >> n;
	long long int sum = 0;
	for (auto i = 0; i < n; ++i) {
		int v; std::cin >> v;
		sum += v;
	}
	std::cout << sum - n * (n + 1) / 2 << std::endl;
}
