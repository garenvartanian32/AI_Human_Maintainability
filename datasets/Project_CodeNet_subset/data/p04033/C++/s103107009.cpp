#include <stdio.h>
#include <algorithm>
using namespace std;

int main(){
    int a,b;

    scanf("%d %d",&a,&b);

    if(a<=0 && b>=0) printf("Zero");
    else if(b<0 && (b-a)%2==0) printf("Negative");
    else printf("Positive");

    return 0;
}
