#include<algorithm>
#include<iostream>
#include<string>
#include<cstdlib>
#include<cstring>
#include<cmath>
using namespace std;
long long fn(long long p,long long q){
  long long s;
  while(q){
    p%=q;
    s=p;
    p=q;
    q=s;
  }
  return p;
}
int main(){
  int i,j,k;
  int n;
  while(cin>>n&&n){
    long long a[31400],b[31400];
    j=0;
    k=0;
    for(i=0;i<n*(n+1)/2;i++){
      long long p;
      cin>>p;
      if(p%2){
	a[j]=p;
	j++;
      }else{
	b[k]=p;
	k++;
      }
    }
    sort(a,a+j);
    sort(b,b+k);
    long long c=fn(a[0],b[0]);
    c=(long long)sqrt(b[0]/c*(b[1]/(a[0]/c)));
    cout<<c<<endl;
    for(i=0;i<k-1;i++)
      cout<<b[i]/c<<" ";
    cout<<b[i]/c<<endl;
  }
  return 0;
}