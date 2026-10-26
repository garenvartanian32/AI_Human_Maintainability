#include<stdio.h>
#include<map>
#include<algorithm>
struct S{int i,u,a,p;}a[300];
int C(S l,S r)
{
	if(l.a-r.a)return l.a>r.a;
	if(l.p-r.p)return l.p<r.p;
	return l.i<r.i;
}
int main()
{
	int i,n,c;
	while(scanf("%d",&n),n)
	{
		for(i=0;i<n;++i)scanf("%d%d%d%d",&a[i].i,&a[i].u,&a[i].a,&a[i].p);
		std::sort(a,a+n,C);
		std::map<int,int>m;
		for(c=i=0;i<n;++i)
		{
			if(c<10&&m[a[i].u]<3 || c<20&&m[a[i].u]<2 || c<26&&m[a[i].u]<1)
				printf("%d\n",a[i].i),m[a[i].u]++,++c;
		}
	}
	return 0;
}