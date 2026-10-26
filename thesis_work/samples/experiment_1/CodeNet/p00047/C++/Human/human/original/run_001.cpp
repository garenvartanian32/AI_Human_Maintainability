#include<cstdio>
#include<iostream>
#include<algorithm>
using namespace std;
int main()
{
    char Ba,a,b;
    int i=1;
    Ba='A';
    while(scanf("%c,%c",&a,&b) != EOF)
    {
        if(Ba==a)Ba=b;
        else if(Ba==b)Ba=a;
        fflush(stdin);
    }
    printf("%c\n",Ba);
    return 0;
}