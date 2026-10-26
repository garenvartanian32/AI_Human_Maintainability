#include <bits/stdc++.h>
using namespace std;
const long long MOD = 1000000007;
int main(){
    int t;
    cin >> t;
    vector<string> ns(t);
    vector<int> ms(t);
    int maxM = 0;
    for (int i=0;i<t;i++){
        cin >> ns[i] >> ms[i];
        maxM = max(maxM, ms[i]);
    }
    vector<vector<long long>> length(10, vector<long long>(maxM+1, 0));
    for (int d=0;d<10;d++) length[d][0]=1;
    for (int s=1;s<=maxM;s++){
        for (int d=0;d<10;d++){
            int nd = d+1;
            string ds = to_string(nd);
            long long sum = 0;
            for (char c: ds) sum = (sum + length[c-'0'][s-1]) % MOD;
            length[d][s] = sum;
        }
    }
    string out;
    for (int i=0;i<t;i++){
        long long total = 0;
        for (char c: ns[i]) total = (total + length[c-'0'][ms[i]]) % MOD;
        out += to_string(total) + "\n";
    }
    cout << out;
    return 0;
}