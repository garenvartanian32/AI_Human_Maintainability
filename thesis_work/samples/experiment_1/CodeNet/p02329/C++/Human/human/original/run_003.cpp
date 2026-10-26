#include <iostream>
#include <vector>
#include <algorithm>

int main() {
	long long int n, v;
	std::cin >> n >> v;
	std::vector<long long int> a(n), b(n), c(n), d(n);
	for (auto &n : a) std::cin >> n;
	for (auto &n : b) std::cin >> n;
	for (auto &n : c)std::cin >> n;
	for (auto &n : d) std::cin >> n;
	std::vector<long long int> ab(n * n), cd(n * n);
	for (auto i = 0; i < n; ++i) {
		for (auto j = 0; j < n; ++j) {
			ab[i * n + j] = a[i] + b[j];
			cd[i * n + j] = c[i] + d[j];
		}
	}
	std::sort(cd.begin(), cd.end());
	long long int sum = 0;
	for (const auto n : ab) {
		sum += std::upper_bound(cd.begin(), cd.end(), v - n) - std::lower_bound(cd.begin(), cd.end(), v - n);
	}
	std::cout << sum << std::endl;
}
