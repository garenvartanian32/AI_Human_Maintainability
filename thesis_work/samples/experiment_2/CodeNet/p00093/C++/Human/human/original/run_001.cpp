#include<cstdio>
main(){int a,b,d=0;while(scanf("%d%d",&a,&b),a){if(d++)puts("");int c=0;for(;a<=b;a++)if(a%4<1)if(a%100)c++,printf("%d\n",a);else if(a%400<1)c++,printf("%d\n",a);if(!c)puts("NA");}}