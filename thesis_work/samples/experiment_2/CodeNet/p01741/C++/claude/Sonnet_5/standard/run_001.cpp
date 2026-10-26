#include <bits/stdc++.h>
using namespace std;

int main() {
    double d;
    cin >> d;
    double best = d * sqrt(2.0);
    int floorD = (int) floor(d + 1e-9);
    for (int k = 1; k <= floorD; k++) {
        double rem = d*d - (double)k*k;
        if (rem < 0) rem = 0;
        double dy = sqrt(rem);
        double val = k + max(1.0, dy);
        if (val > best) best = val;
    }
    printf("%.12f\n", best);
    return 0;
}