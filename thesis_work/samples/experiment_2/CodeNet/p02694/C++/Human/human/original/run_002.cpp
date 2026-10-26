#include<bits/stdc++.h>
using namespace std;
#define lli long long int
#define rep(i,s,e) for(int i=s;i<e;i++)
#define endl '\n'
int main(){
    lli x;cin>>x;
    lli base=100;
    double rate=0.01;
    int res=0;
    while(base<x){
        base*=(1+rate);
        res++;
    }
    cout<<res<<endl;
}