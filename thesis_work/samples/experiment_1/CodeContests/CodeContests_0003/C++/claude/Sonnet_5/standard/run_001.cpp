#include <bits/stdc++.h>
using namespace std;
int main(){
    int n;
    cin >> n;
    vector<int> deg(n+1, 0);
    for (int i=0;i<n-1;i++){
        int u,v;
        cin >> u >> v;
        deg[u]++; deg[v]++;
    }
    bool ok = true;
    for (int i=1;i<=n;i++) if (deg[i]==2) { ok=false; break; }
    cout << (ok ? "YES" : "NO") << endl;
    return 0;
}