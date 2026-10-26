#include <bits/stdc++.h>
using namespace std;
int main(){
    int n, m;
    cin >> n >> m;
    set<int> on;
    for (int i=0;i<n;i++){
        int x;
        cin >> x;
        for (int j=0;j<x;j++){
            int v;
            cin >> v;
            on.insert(v);
        }
    }
    cout << ((int)on.size() == m ? "YES" : "NO") << endl;
    return 0;
}