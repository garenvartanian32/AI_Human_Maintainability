#include<iostream>
#include<string>
#include<iomanip>
#include<cmath>
#include<vector>
#include<algorithm>
#include<utility>

using namespace std;

#define int long long
#define endl "\n"

constexpr long long INF = (long long)1e18;
constexpr long long MOD = 1'000'000'007; 

struct fast_io {
	fast_io(){
		std::cin.tie(nullptr);
		std::ios::sync_with_stdio(false);
	};
// };
} fio;

int N;
vector<string> y;

void dfs2(string s){
	
	if(s.size() == N){
		cout<<s<<endl;
		y.push_back(s);
		return ;
	}
	for(int i = 0; i <= s[(int)s.size()-1] - 'a' + 1; i++){
		char c = i + 'a';
		
		dfs2(s + c);
	}
}

void dfs(string s){
	
	if(s.size() == N){
		// cout<<s<<endl;
		y.push_back(s);
		return ;
	}
	// for(int i = 0; i <= s[(int)s.size()-1] - 'a' + 1; i++){
		// char c = i + 'a';
		
		// dfs(s + c);
	// }
	int con[26] = {};
	
	for(int i = 0; i < s.size(); i++){
		con[s[i] - 'a'] = true;
		con[s[i] - 'a' + 1] = true;
	}
	
	for(int i = 0; i < 26; i++){
		if(con[i] == false) break;
		char c = i + 'a';
		
		dfs(s+c);
	}
}
/*
$ ./a.exe

4
aaaa
aaab
aaba
aabb
aabc
abaa
abab
abba
abbb
abbc
abca
abcb
abcc
abcd





4
aaaa
aaab
aaba
aabb
aabc
abaa
abab
abac
abba
abbb
abbc
abca
abcb
abcc
abcd

*/
signed main(){
	cout<<fixed<<setprecision(10);
	string x;
	
	x = "a";
	
	cin>>N;
	
	dfs(x);
	// x[0] = 'b';
	// dfs(x);
	
	// cout<<y.size()<<endl;
	
	sort(y.begin(), y.end());
	for(int i = 0; i < y.size(); i++){
		cout<<y[i]<<endl;
	}
	return 0;
	for(int i = 0; i < y.size(); i++){
		for(int j = i+1; j < y.size(); j++){
			bool flag = true;
			for(int k = 0; k < N; k++){
				for(int l = 0; l < N; l++){
					if(y[i][k] == y[i][l] && y[j][k] == y[j][l]) {
						
					} else if(y[i][k] != y[i][l] && y[j][k] != y[j][l]){
					} else {
						flag = false;
					}
				}
			}
			
			if(flag) {
				cout<<"ZZZ "<<y[i]<<" "<<y[j]<<endl;
			}
		}
	}
	
	return 0;
}