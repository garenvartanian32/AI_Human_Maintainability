#include<bits/stdc++.h>
using namespace std;

int N;
int f[100],a[100],t[100],x[100],y[100];

int main(){
    cin>>N;
    for(int i=0;i<N;i++)cin>>f[i]>>a[i]>>t[i]>>x[i]>>y[i];
    for(int b=0;b<2;b++){
        int dist=-1,tim=-1;

        for(int i=0;i<N-1;i++){
            if(t[i]==t[i+1]&&t[i]==b&&a[i]!=a[i+1]){
                int d=(x[i]-x[i+1])*(x[i]-x[i+1])+(y[i]-y[i+1])*(y[i]-y[i+1]);
                int x=f[i+1]-f[i];
                if(dist<d){
                    dist=d;
                    tim=x;
                }
                else if(dist==d){
                    tim=min(tim,x);
                }
            }
        }
        if(dist==-1)puts("-1 -1");
        else printf("%.20lf %.20lf\n",sqrt(dist),tim/60.0);
    }
    return 0;
}