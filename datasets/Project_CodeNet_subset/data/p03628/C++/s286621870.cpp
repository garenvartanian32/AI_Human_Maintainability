#include <iostream>
#include <vector>

using namespace std;

long long mod = 1000000007;

long long mul_mod(long long x,long long y){
    return x*y%mod;
}

int main(){
    string str[2];
    int n;
    cin >> n;
    cin >> str[0];
    cin >> str[1];
    long long ans = 1;
    int did = 0;
    int last;
    if(str[0][0] == str[1][0]){
        ans = 3;
        last = 1;
        did = 1;
    }
    else{
        ans = 6;
        last = 2;
        did = 2;
    }
    while(did < n){
        if(str[0][did] == str[1][did]){
            if(last == 1) ans = mul_mod(ans,2);
            did += 1;
            last = 1;
        }
        else{
            if(last == 1) ans = mul_mod(ans,2);
            else ans = mul_mod(ans,3);
            did += 2;
            last = 2;
        }
    }
    cout << ans << endl;
}
