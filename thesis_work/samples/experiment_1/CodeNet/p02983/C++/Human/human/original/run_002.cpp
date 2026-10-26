#include <iostream>
using namespace std;
int main(){
	long long a,b,min1(999999999);
	cin>>a>>b;
	for(long long i=a;i<b;i++){
		for(long long j=i+1;j<=b;j++){
			if((i*j)%2019<min1) min1=(i*j)%2019;
			if(!min1) break;
		}
		if(!min1) break;
	}
	cout<<min1;
}