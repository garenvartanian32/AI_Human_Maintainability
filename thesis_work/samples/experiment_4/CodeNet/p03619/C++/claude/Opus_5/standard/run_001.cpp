#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    long long x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;
    int n;
    cin >> n;
    vector<long long> X(n), Y(n);
    for (int i = 0; i < n; i++) cin >> X[i] >> Y[i];

    double r = 10.0;
    long long dx = llabs(x1 - x2);
    long long dy = llabs(y1 - y2);
    double manhattan = (dx + dy) * 100.0;

    if (x1 == x2 || y1 == y2) {
        double penalty = 0.0;
        if (y1 == y2) {
            long long lo = min(x1, x2), hi = max(x1, x2);
            for (int i = 0; i < n; i++) {
                if (Y[i] == y1 && lo < X[i] && X[i] < hi) {
                    penalty = r * (M_PI - 2);
                    break;
                }
            }
        } else {
            long long lo = min(y1, y2), hi = max(y1, y2);
            for (int i = 0; i < n; i++) {
                if (X[i] == x1 && lo < Y[i] && Y[i] < hi) {
                    penalty = r * (M_PI - 2);
                    break;
                }
            }
        }
        printf("%.15f\n", manhattan + penalty);
        return 0;
    }

    long long xlo = min(x1, x2), xhi = max(x1, x2);
    long long ylo = min(y1, y2), yhi = max(y1, y2);
    int xdir = x2 > x1 ? 1 : -1;
    int ydir = y2 > y1 ? 1 : -1;

    vector<pair<long long,long long>> candidates;
    for (int i = 0; i < n; i++) {
        if (xlo < X[i] && X[i] < xhi && ylo < Y[i] && Y[i] < yhi) {
            candidates.push_back({X[i] * xdir, Y[i] * ydir});
        }
    }
    sort(candidates.begin(), candidates.end());

    vector<long long> tails;
    for (auto &cand : candidates) {
        long long ty = cand.second;
        auto it = lower_bound(tails.begin(), tails.end(), ty);
        if (it == tails.end()) tails.push_back(ty);
        else *it = ty;
    }
    int len = tails.size();

    double bonus = r * (2 - M_PI / 2);
    double answer = manhattan - len * bonus;
    printf("%.15f\n", answer);
    return 0;
}