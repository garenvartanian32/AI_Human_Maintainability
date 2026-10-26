#include<bits/stdc++.h>
using namespace std;
int main(void)
{
	int n;
	int tefud[11];
	int hito;
	int ba;
	int i;
	char yama[101];
	
	while(1){
		
		cin>>n;
		
		if(n==0) break;
		
		for(i=1;i<=n;i++) tefud[i]=0;
		
		cin>>yama;
		
		hito=1; ba=0;
		for(i=0;i<100;i++){
			if(hito>n) hito=1;
			if(yama[i]=='M') tefud[hito]++;
			else if(yama[i]=='S'){
				ba+=tefud[hito]+1;
				tefud[hito]=0;
			}
			else if(yama[i]=='L'){
				tefud[hito]+=ba+1;
				ba=0;
			}
			hito++;
		}
		
		sort(tefud+1,tefud+n+1);
		
		for(i=1;i<=n;i++) cout<<tefud[i]<<" ";
		cout<<ba<<endl;
		
	}
	return 0;
}
