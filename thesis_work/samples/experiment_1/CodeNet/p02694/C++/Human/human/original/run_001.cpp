#include<bits/stdc++.h>
using namespace std;

typedef long long ll;
int main()
{
    ll x;
    cin>>x;
    ll initial=100;
    ll cnt=0;
    while(initial<x)
    {
        initial+=(initial/100);
        cnt++;
    }
    cout<<cnt<<endl;
    return 0;
}