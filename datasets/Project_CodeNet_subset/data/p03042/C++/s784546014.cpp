#include <bits/stdc++.h>
using namespace std;
#define rep(i,n) for(ll i=0; i<n; i++)
typedef long long ll;
ll mod = 1000000007;

/*
月になりうる
*/

bool month(char a, char b){
    return ((a=='0' && b!='0') || (a=='1' && ((0 <= (b -'0')) && ((b-'0') <= 2))));
}


void solve(){
    string s;
    cin >> s;
    char s1 = s[0];
    char s2 = s[1];
    char s3 = s[2];
    char s4 = s[3];
    string st;
    if(month(s1,s2)&&month(s3,s4)){
        st = "AMBIGUOUS";
    }
    else if(month(s1,s2)){
        st = "MMYY";
    }
    else if(month(s3,s4)){
        st = "YYMM";
    }
    else{
        st = "NA";
    }
    cout << st << endl;
}

int main(){
  cin.tie(0);
  ios::sync_with_stdio(false);
  solve();
  return 0;
}