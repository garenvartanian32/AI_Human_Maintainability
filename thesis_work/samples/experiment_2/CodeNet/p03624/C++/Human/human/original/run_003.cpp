//include
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <cmath> 
#include <iomanip>
#include <math.h>
#include <utility>
#include <functional>
#include <cstdlib>
//using
using namespace std;
using vl = vector <long long>;
using vs = vector <string>;
using vc = vector <char>;
using ll= long long;
using vvl = vector<vector<ll> >;
//vector<vector<char> > hyou(N, vector<char>(N));
using vd = vector <double>;
//define
//#define int long long
#define rep(i,n) for(int i=0; i<n; i++)
#define print(n) cout<<n<<endl;
#define sortp(d) sort(d.begin(),d.end()) //1234
#define sortm(d) sort(d.rbegin(),d.rend()) //4321
//素数判定　O(√A) 
bool is_prime(int x){
 if(x<=1) return false;
 for(int i=2;i*i<=x;i++)
 if(x%i==0) return false;
 return true;
 }
/*順列生成
do {
  // 順列に対する処理
} while (next_permutation(配列変数.begin(), 配列変数.end()));*/

//絶対値　abs()
//文字→数字　'0' アルファベット→数字　'a'

//グローバル変数宣言
 ll p=0,q=0,r=0;

int main()
{
  string S;
	cin>>S;

	bool app[26];
	for (int i = 0; i < 26; ++i) {
		app[i] = false;
	}
	for (int i = 0; i < S.size(); ++i) {
		app[S[i] - 'a'] = true;
	}
	for (int i = 0; i < 26; ++i) {
		if (app[i]==false) {
			printf("%c\n", (char)(i + 'a'));
			return 0;
		}
	}
	puts("None");
	return 0;
}
