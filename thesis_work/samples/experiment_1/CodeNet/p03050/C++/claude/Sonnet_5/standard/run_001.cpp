#include <bits/stdc++.h>
using namespace std;
int main(){
    long long n;
    cin >> n;
    long long total=0, q=1;
    while (q*q < n) {
        if (n % q == 0) {
            long long m = n/q - 1;
            if (q < m) total += m;
        }
        q++;
    }
    cout << total << endl;
    return 0;
}