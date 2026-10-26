#include <stdio.h>
#include <cmath>

using namespace std;

int main()
{
    double d;
    scanf("%lf", &d);

    double ans = (int)d + 1 > d * sqrt(2.0) ? (int)d + 1 : d * sqrt(2.0);
    printf("%.12lf", ans);

    return 0;
}