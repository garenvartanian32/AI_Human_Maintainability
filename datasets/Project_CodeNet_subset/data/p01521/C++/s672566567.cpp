#include <iostream>
#include <string>
using namespace std;

int countFlip(const string& str) {
	int cnt = 0;
	char c = str[0];
	for (unsigned i = 1; i < str.size(); i++) {
		if (c != str[i]) {
			cnt++;
			c = str[i];
		}
	}
	return cnt;
}

int main () {
	string field;
	cin >> field;
	
	int c = countFlip(field);
	if (c & 1) {
		cout << 'o' << endl;
	}
	else {
		cout << field[0] << endl;
	}
	
	return 0;
}