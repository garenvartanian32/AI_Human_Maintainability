#include <iostream>
#include <cstdio>
#include <string>
#include <cctype>

using namespace std;

int main(){
	string s,
		   ans,
		   end = "END_OF_TEXT";
	int num = 0;

	cin >> ans;
	for(int i = 0; i < ans.length(); ++i){
		if(isupper(ans[i])) ans[i]+=32;
	}
	while(cin >> s){
		if(s == end) break;
		for(int i = 0; i < s.length(); ++i){
			if(isupper(s[i])) s[i]+=32;
		}
		if(s == ans) ++num;
	}
	cout << num << endl;

	return 0;
}