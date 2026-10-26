#include <algorithm>
#include <iostream>
#include <cstring>
#include <cstdio>
#include <cmath>
#define N 200005
#define pii pair<int, int>
using namespace std;
const long double pi = acos(-1);

int x, y, xx, yy, n, posx, posy, f[N];
pii p[N];

int main() {
    cin >> x >> y >> xx >> yy;
    cin >> n, posx = posy = 1;

    if (x > xx) posx = -1;
    if (y > yy) posy = -1;

    x *= posx, xx *= posx;
    y *= posy, yy *= posy;

    for (int i = 1, a, b; i <= n; ++i) {
        scanf("%d%d", &a, &b);
        a *= posx, b *= posy;
        if (a < x || a > xx || b < y || b > yy)
            --n, --i;
        else p[i] = make_pair(a, b);
    }

    sort(p + 1, p + n + 1);

    memset(f, 0x3f, sizeof f);
    for (int i = 1; i <= n; ++i)
        f[lower_bound(f + 1, f + n + 1, p[i].second) - f] = p[i].second;

    int lis = n; while (lis >= 1 && f[lis] == 0x3f3f3f3f) --lis;

    long double ans = ((xx - x) + (yy - y)) * 100.0;
    ans += (pi * 5.0 - 20) * lis;

    if (lis == min(xx - x, yy - y) + 1) ans += pi * 5.0;

    printf("%.46Lf\n", ans);

    return 0;
}
