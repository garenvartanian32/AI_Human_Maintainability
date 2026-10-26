#include <bits/stdc++.h>
using namespace std;
int main(){
    long long a,b;
    cin >> a >> b;
    long long total=a, stubs=a;
    while (stubs >= b) {
        long long nw = stubs/b;
        total += nw;
        stubs = stubs%b + nw;
    }
    cout << total << endl;
    return 0;
}