#include <bits/stdc++.h>
using namespace std;
int main(){
    long long s;
    cin >> s;
    map<long long,int> seen;
    long long a = s;
    int i = 1;
    seen[a] = i;
    while (true) {
        if (a % 2 == 0) a /= 2;
        else a = 3*a+1;
        i++;
        if (seen.count(a)) { cout << i << endl; return 0; }
        seen[a] = i;
    }
}