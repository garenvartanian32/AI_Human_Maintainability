#include<iostream>
#include<sstream>
#include<fstream>
#include<string>
#include<vector>
#include<deque>
#include<queue>
#include<stack>
#include<set>
#include<map>
#include<algorithm>
#include<functional>
#include<utility>
#include<bitset>
#include<cmath>
#include<cstdlib>
#include<ctime>
#include<cstdio>
#include<cstring>
#define M 1000000007
using namespace std;
int n,pre,pos;
queue<int> q;
string s1,s2;
long long res;
int main(){
	std::ios::sync_with_stdio(false);
	cin>>n>>s1>>s2;s1+='*';
	for (int i=0;i<n;i++)
		if (s1[i]==s1[i+1])	q.push(2),i++;else q.push(1);
	pos=q.front();q.pop();
	if (pos==1) res=3;else res=6;
	while (!q.empty()){
		pre=pos;pos=q.front();q.pop();
		if (pre==pos) {if (pre==1) res=(res*2)%M;else res=(res*3)%M;}
		else	if (pre==1) res=(res*2)%M;
	}
	cout<<res;
	return 0;
}