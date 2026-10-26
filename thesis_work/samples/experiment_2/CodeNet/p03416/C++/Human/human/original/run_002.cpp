#include <bits/stdc++.h>
#include <string>
using namespace std;
using ll=long long;
#define rep(i,n) for(int i=0;i<(int)(n);i++)
#define inf 1001001001
typedef pair<int,int> P;
#define mod 1000000007

int main(){
    int a,b;
    cin >> a >> b;
    int ans=0;
    for(int i=a;i<=b;i++){
        string x;
        x=to_string(i);
        int keta=log10(i);
        int count=0;
        for(int j=0;j<=keta/2;j++){
            if(x[j]==x[keta-j]) count++;
        }
        if(count==1+keta/2) ans++;
    }
    cout << ans << endl;
    return 0;
}