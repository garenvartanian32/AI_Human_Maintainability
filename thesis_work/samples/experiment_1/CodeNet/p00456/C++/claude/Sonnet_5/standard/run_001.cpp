#include <bits/stdc++.h>
using namespace std;
int main(){
    vector<int> vals(20);
    for (int i=0;i<20;i++) cin >> vals[i];
    vector<int> w(vals.begin(), vals.begin()+10);
    vector<int> k(vals.begin()+10, vals.end());
    sort(w.rbegin(), w.rend());
    sort(k.rbegin(), k.rend());
    int sw = w[0]+w[1]+w[2];
    int sk = k[0]+k[1]+k[2];
    cout << sw << " " << sk << endl;
    return 0;
}