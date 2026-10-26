#include<bits/stdc++.h>
using namespace std;

int main(void)
{
	string s;
	cin >> s;
	sort(s.begin(), s.end());
	if (s == "abc")cout << "Yes";
	else cout << "No";
}