#include<bits/stdc++.h>
#define rep(i,n)for(int i=0;i<n;i++)
using namespace std;

int main() {
	string s;cin>>s;
	char back='i';
	int cnt=0;
	for(char c:s){
		if(back!=c){
			cnt++;
			back=c;
		}
	}
	cnt--;
	if(*s.begin()=='o'&&*s.rbegin()=='o')puts(cnt&1?"x":"o");
	else puts(cnt&1?"o":"x");
}