#include<iostream>
#include<algorithm>
using namespace std;

int gcd(int a,int b){return b?gcd(b,a%b):a;}

int main(){
  int a,b,c;

  while(cin >> a >> b >> c,a||b||c){
    int d1 = 2*a,d2 = 2*a;
    int u1 = b,u2 = b;
    int tmp = b*b - 4*a*c;

    bool f = false;
    for(int i=0;i*i<=tmp;i++)
      if(i*i==tmp){
	tmp = i;
	f = true;
      }
    
    if(!f){
      cout << "Impossible\n";
      continue;
    }

    u1 += tmp;
    u2 -= tmp;
    
    tmp = gcd(u1,d1);
    u1 /= tmp; d1/= tmp;
    tmp = gcd(u2,d2);
    u2 /= tmp; d2/= tmp;
    
    if(d1<0){u1*=-1; d1*=-1;}
    if(d2<0){u2*=-1; d2*=-1;}

    if(d1<d2 || (d1==d2 && u1<u2)){swap(u1,u2); swap(d1,d2);}
    
    cout << d1 << " " << u1 << " " << d2 << " " << u2 << endl;
  }
}