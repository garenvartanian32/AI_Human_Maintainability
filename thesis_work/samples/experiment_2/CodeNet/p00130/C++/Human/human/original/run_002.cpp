#include <iostream>
#include <deque>
#include <string>
using namespace std;

int main(){
	int n;cin >> n;
	string s;
	while(n--){
		deque<char> d;
		cin >> s;
		int p = 0;
		d.push_back(s[0]);
		for(int i = 1;i < s.size();i += 3){
			if(s[i] == '<'){
				if(!p)d.push_front(s[i+2]);
				else p--;
			}else{
				if(p == d.size()-1)d.push_back(s[i+2]);
				p++;
			}
		}
		for(int i = 0;i < d.size();i++)cout << d[i];
		cout << endl;
	}
	return 0;
}