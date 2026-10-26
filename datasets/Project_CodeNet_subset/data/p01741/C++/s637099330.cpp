#include <bits/stdc++.h>
using namespace std;
int main(){
  double d,p;
  cin>>d;
  p=(int)d+1>d*sqrt(2)?(int)d+1:d*sqrt(2);
  printf("%.11f\n",p);
}