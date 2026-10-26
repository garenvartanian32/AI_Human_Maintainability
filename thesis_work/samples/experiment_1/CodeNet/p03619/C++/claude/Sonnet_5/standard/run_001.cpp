#include <bits/stdc++.h>
using namespace std;
int main(){
    long long x1,y1,x2,y2;
    cin >> x1 >> y1 >> x2 >> y2;
    int n;
    cin >> n;
    set<pair<long long,long long>> fset;
    for (int i=0;i<n;i++){
        long long X,Y;
        cin >> X >> Y;
        fset.insert({X,Y});
    }
    double base = fabs((double)(x1-x2))*100.0 + fabs((double)(y1-y2))*100.0;
    bool c1 = fset.count({x1,y2});
    bool c2 = fset.count({x2,y1});
    if (!c1 && !c2) {
        printf("%.12f\n", base);
        return 0;
    }
    double extra = (M_PI/2 - 2) * 10;
    printf("%.12f\n", base + extra);
    return 0;
}