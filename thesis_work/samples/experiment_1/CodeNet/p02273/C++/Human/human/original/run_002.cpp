#include <iostream>
#include <math.h>
#include <stdio.h>

using namespace std;
struct point{
    double x,y;
};
void koch(int n,point a,point b){
    if(n==0) return ;
    point s,t,u;
    s.x=(2*a.x+b.x)/3;
    s.y=(2*a.y+b.y)/3;
    
    t.x=(2*b.x+a.x)/3;
    t.y=(2*b.y+a.y)/3;
    
    u.x=(t.x-s.x)/2-(t.y-s.y)*sqrt(3)/2+s.x;
    u.y=(t.x-s.x)*sqrt(3)/2+(t.y-s.y)/2+s.y;
    
    koch(n-1,a,s);
    printf("%.8f %.8f\n",s.x,s.y);
    koch(n-1,s,u);
    printf("%.8f %.8f\n",u.x,u.y);
    koch(n-1,u,t);
    printf("%.8f %.8f\n",t.x,t.y);
    koch(n-1,t,b);
}
int main(){
    point a,b;
    int n;
    scanf("%d",&n);
    
    a.x=0;
    a.y=0;
    b.x=100;
    b.y=0;
    
    printf("%.8f %.8f\n",a.x,a.y);
    koch(n,a,b);
    printf("%.8f %.8f\n",b.x,b.y);
    
    return 0;
}

