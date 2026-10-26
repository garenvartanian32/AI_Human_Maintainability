#include <iostream>
#include <vector>
#include <string>
using namespace std;

string temp = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

char change(char c, int num)
{
	int cur;

	for (int i = 0; i < 52; i++) {
		if (temp[i] == c) {
			cur = i;
		}
	}

	cur -= num;

	if (cur < 0) {
		cur += 52;
	}

	return temp[cur];
}

string solve(string str, vector<int> k, int vec_size)
{
	unsigned int str_size = str.size();

	int cur = 0;
	for (unsigned int i = 0; i < str_size; i++) {
		str[i] = change(str[i], k[cur]);
		cur = (cur + 1) % vec_size;
	}

	return str;
}

int main()
{
	while (true) {
		int n;
		cin >> n;
		
		if (n == 0) {
			break;
		}

		vector<int> k(n);

		for (int i = 0; i < n; i++) {
			cin >> k[i];
		}

		string str;
		cin >> str;

		cout << solve(str, k, n) << endl;
	}

	return 0;
}