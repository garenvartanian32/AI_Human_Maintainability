#include <iostream>
#include <string>
#include <vector>
#include <cmath>

using namespace std;

using ll = long long;

ll mod = 1000000007;

int main(int argc, char const *argv[]) {
	string str;
	std::cin >> str;
	int q = 0;

	// ? の数を数える
	for (size_t i = 0; i < str.size(); i++) {
		if (str[i] == '?') {
			q++;
		}
	}


	//3^qを計算しておく
	std::vector<ll> calc3q(str.size()+2);
	calc3q[0] = 1;
	for (size_t i = 1; i < calc3q.size(); i++) {
		calc3q[i] = (calc3q[i-1] * 3) % mod;
	}


	// left : [number of a, number of ?]pairのvector
	// right: [number of c, number of ?]pairのvector
	std::vector<std::vector<ll>> left(str.size(),std::vector<ll> (2,0)),right(str.size(),std::vector<ll> (2,0));
	for (size_t i = 1; i < str.size(); i++) {
		if (str[i-1] == 'A') {
			left[i][0] = left[i-1][0] + 1;
			left[i][1] = left[i-1][1];
		}
		else if (str[i-1] == '?') {
			left[i][0] = left[i-1][0];
			left[i][1] = left[i-1][1] + 1;
		}
		else {
			left[i][0] = left[i-1][0];
			left[i][1] = left[i-1][1];
		}
	}
	for (int i = str.size()-2; i > -1; i--) {
		if (str[i+1] == 'C') {
			right[i][0] = right[i+1][0] + 1;
			right[i][1] = right[i+1][1];
		}
		else if (str[i+1] == '?') {
			right[i][0] = right[i+1][0];
			right[i][1] = right[i+1][1] + 1;
		}
		else {
			right[i][0] = right[i+1][0];
			right[i][1] = right[i+1][1];
		}
	}
/*
	for (size_t i = 0; i < right.size(); i++) {
		std::cout << right[i][0] << ' ';
	}
	std::cout << '\n';
	for (size_t i = 0; i < right.size(); i++) {
		std::cout << right[i][1] << ' ';
	}
	std::cout << '\n';
	for (size_t i = 0; i < left.size(); i++) {
		std::cout << left[i][0] << ' ';
	}
	std::cout << '\n';
	for (size_t i = 0; i < left.size(); i++) {
		std::cout << left[i][1] << ' ';
	}
	std::cout << '\n';
*/

	ll ans = 0;
	for (size_t i = 1; i < str.size()-1; i++) {
		if (str[i] != 'B' && str[i] != '?') {
			continue;
		}
		if (str[i] == 'B') {
			// A B Cの場合
			ans = (ans + (((left[i][0] * right[i][0])%mod) * calc3q[q]) % mod) % mod;
			// ? B Cの場合
			ans = (ans + (((left[i][1] * right[i][0])%mod) * calc3q[q-1]) % mod) % mod;
			// A B ?の場合
			ans = (ans + (((left[i][0] * right[i][1])%mod) * calc3q[q-1]) % mod) % mod;
			// ? B ?の場合
			ans = (ans + (((left[i][1] * right[i][1])%mod) * calc3q[q-2]) % mod) % mod;
		}
		if (str[i] == '?') {
			// A ? Cの場合
			ans = (ans + (((left[i][0] * right[i][0])%mod) * calc3q[q-1]) % mod) % mod;
			// ? ? Cの場合
			ans = (ans + (((left[i][1] * right[i][0])%mod) * calc3q[q-2]) % mod) % mod;
			// A ? ?の場合
			ans = (ans + (((left[i][0] * right[i][1])%mod) * calc3q[q-2]) % mod) % mod;
			// ? ? ?の場合
			ans = (ans + (((left[i][1] * right[i][1])%mod) * calc3q[q-3]) % mod) % mod;
		}
	}

	std::cout << ans << '\n';
	return 0;
}
