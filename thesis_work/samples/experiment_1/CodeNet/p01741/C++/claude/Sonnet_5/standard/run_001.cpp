#include <bits/stdc++.h>
using namespace std;
int main(){
    double d;
    cin >> d;
    double best = d * sqrt(2.0);
    for (int dy = 1; dy <= d + 1e-12; dy++) {
        double valSq = d*d - (double)dy*dy;
        if (valSq >= -1e-12) {
            double dx = sqrt(max(0.0, valSq));
            if (dx < 1 - 1e-12) {
                best = max(best, (double)dy + 1);
            }
        }
    }
    printf("%.12f\n", best);
    return 0;
}