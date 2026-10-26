#include <bits/stdc++.h>
using namespace std;
int main(){
    int n;
    cin >> n;
    int c1=0,c2=0;
    for (int i=0;i<n;i++){
        int x;
        cin >> x;
        if (x==100) c1++; else c2++;
    }
    if (c1 % 2 != 0) { cout << "NO" << endl; return 0; }
    if (c1 == 0 && c2 % 2 != 0) { cout << "NO" << endl; return 0; }
    cout << "YES" << endl;
    return 0;
}