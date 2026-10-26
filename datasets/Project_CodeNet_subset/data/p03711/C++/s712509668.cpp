#include<iostream>
#include<sstream>
#include<fstream>
#include<stdio.h>
#include<algorithm>
#include<string.h>
#include<string>
#include<queue>
#include<set>
#include<vector>
#include<cmath>
#include<ctime>
#include<locale>
using namespace std;
long v[12]={1,3,1,2,1,2,1,1,2,1,2,1};
int main()
{
    long a,b;
 
    cin>>a>>b;
    if(v[a-1]==v[b-1])cout<<"Yes";
    else cout<<"No";
	return 0;
}