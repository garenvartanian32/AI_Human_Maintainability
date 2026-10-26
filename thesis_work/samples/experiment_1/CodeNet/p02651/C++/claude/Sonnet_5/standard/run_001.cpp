#include <bits/stdc++.h>
using namespace std;
char solve(int n, vector<long long>& A, string& S) {
    vector<long long> basis(64, 0);
    char lastOwner = '0';
    for (int i = n-1; i >= 0; i--) {
        long long cur = A[i];
        for (int b = 63; b >= 0; b--) {
            if (!((cur>>b)&1)) continue;
            if (basis[b] == 0) {
                basis[b] = cur;
                lastOwner = S[i];
                cur = 0;
                break;
            } else {
                cur ^= basis[b];
            }
        }
    }
    return lastOwner;
}
int main(){
    int t;
    cin >> t;
    string out;
    for (int i=0;i<t;i++){
        int n;
        cin >> n;
        vector<long long> A(n);
        for (auto &v: A) cin >> v;
        string S;
        cin >> S;
        out += solve(n, A, S);
        out += "\n";
    }
    cout << out;
    return 0;
}