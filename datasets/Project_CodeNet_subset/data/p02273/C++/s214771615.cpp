#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cstring>
#include <vector>
#include <cmath>
using namespace std;
const double PI = acos(-1.0), theta = PI / 3.0;
struct point
{
    double x, y;
};
struct segment
{
    point a, b;
};
int n;
vector<segment> v1, v2;
int main(int argc, char const *argv[])
{
    // freopen("my.out", "w", stdout);
    scanf("%d", &n);
    v2.push_back((segment){(point){0.0, 0.0}, (point){100.0, 0.0}});
    for (int i = 1; i <= n; i++)
    {
        swap(v1, v2);
        v2.clear();
        for (int i = 0; i < (int)v1.size(); i++)
        {
            segment t = v1[i];
            point a = t.a, b = t.b;
            double x_1 = a.x, y_1 = a.y, x_5 = b.x, y_5 = b.y, x_2 = (x_1 * 2.0 + x_5) / 3.0, y_2 = (y_1 * 2.0 + y_5) / 3.0, x_4 = (x_1 + x_5 * 2.0) / 3.0, y_4 = (y_1 + y_5 * 2.0) / 3.0, dx = x_4 - x_2, dy = y_4 - y_2, x_3 = x_2 + dx * cos(theta) - dy * sin(theta), y_3 = y_2 + dx * sin(theta) + dy * cos(theta);
            v2.push_back((segment){(point){x_1, y_1}, (point){x_2, y_2}});
            v2.push_back((segment){(point){x_2, y_2}, (point){x_3, y_3}});
            v2.push_back((segment){(point){x_3, y_3}, (point){x_4, y_4}});
            v2.push_back((segment){(point){x_4, y_4}, (point){x_5, y_5}});
        }
    }
    for (int i = 0; i < (int)v2.size(); i++)
    {
        printf("%.8lf %.8lf\n", v2[i].a.x, v2[i].a.y);
    }
    printf("%.8lf %.8lf", v2.rbegin()->b.x, v2.rbegin()->b.y);
    fclose(stdout);
    return 0;
}
