#include<iostream>
#include<cstdio>
#include<algorithm>
#include<cstring>
using namespace std;
main(){
  char a,b;
  int cup[256]={};
  cup['A']=1;
  while(~scanf("%c,%c ",&a,&b)){
    swap(cup[a],cup[b]);
  }
  //  printf("%d %d %d\n",cup['A'],cup['B'],cup['C']);
  if(cup['A']==1) puts("A");
  else if(cup['B']==1) puts("B");
  else puts("C");
}