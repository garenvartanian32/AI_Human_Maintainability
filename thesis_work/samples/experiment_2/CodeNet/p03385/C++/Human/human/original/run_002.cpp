#include<iostream>
#include<algorithm>
#include<string.h>
#include<string>

using namespace std;
char a;
int num[200];
int main()
{
	for (int i = 0; i < 3; i++)
	{
		cin >> a;
		num[a]++;
		if (num[a] > 1)
		{
			cout << "No";
			return 0;
		}
	}
	cout << "Yes";
	return 0;

 }