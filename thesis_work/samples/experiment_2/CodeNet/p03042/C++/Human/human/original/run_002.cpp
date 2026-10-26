#include <iostream>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <string>
using namespace std;

int main(){
    //freopen("in.txt", "r", stdin);
    string s;
    cin>>s;

    int n1, n2;
    n1 = s[0] - '0';
    n1 *= 10; 
    n1 += s[1] - '0';

    n2 = s[2] - '0';
    n2 *= 10;
    n2 += s[3] - '0';

    bool YYMM = false, MMYY = false;

    if (n2 > 0 && n2 <= 12) YYMM = true;
    if (n1 > 0 && n1 <= 12) MMYY = true;

    if (YYMM && MMYY) cout<<"AMBIGUOUS"<<endl;
    else if (YYMM) cout<<"YYMM"<<endl;
    else if (MMYY) cout<<"MMYY"<<endl;
    else cout<<"NA"<<endl;

    return 0;
}