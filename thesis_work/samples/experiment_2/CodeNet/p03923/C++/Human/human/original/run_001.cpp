#include <iostream>
#define LL long long
using namespace std;

LL N,A;

bool check(LL x,LL p1, LL p2){
    LL i,aux=1;
    for (i=1; i<=p1; i++){
        aux*=x;
        if (aux>=N) return 1;
    }

    for (i=1; i<=p2; i++){
        aux*=(x+1);
        if (aux>=N) return 1;
    }

    return 0;
}

int main(){
    cin >> N >> A;

    LL res=N;

    LL k,t,x,l,r,mid;
    for (k=2; k<=40; k++){
        for (t=0; t<=k; t++){
            l=1,r=2000000;
            while (l<r){
                mid=(l+r)/2;
                if (check(mid,t,k-t)) r=mid;
                else l=mid+1;
            }

            x=r;
            res=min(res,k*(x+1)-t+(k-1)*A);
        }
    }

    cout << res << "\n";
    return 0;
}
