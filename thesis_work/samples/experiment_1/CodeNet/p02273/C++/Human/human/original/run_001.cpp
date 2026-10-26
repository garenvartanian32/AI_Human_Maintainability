#include <iostream>
#include <cmath>

using std::cin;

constexpr double PI = 3.1415926535;

class Point {
public:
    double x;
    double y;

    Point(double xx = 0.0, double yy = 0.0)
        : x(xx), y(yy) {}
};

void calcKochCurvePoint(int n, Point& p1, Point& p2);
void printPoint(Point& p);

int main()
{
    int n;
    cin >> n;

    Point p1(0.0, 0.0);
    Point p2(100.0, 0.0);

    printPoint(p1);
    calcKochCurvePoint(n, p1, p2);
    printPoint(p2);

    return 0;
}

void calcKochCurvePoint(int n, Point& p1, Point& p2)
{
    if (n == 0) {
        return ;
    }

    Point s, t, u;
    s.x = (2 * p1.x + p2.x) / 3;
    s.y = (2 * p1.y + p2.y) / 3;
    t.x = (p1.x + 2 * p2.x) / 3;
    t.y = (p1.y + 2 * p2.y) / 3;
    u.x = s.x + (t.x - s.x) * cos(PI / 3) - (t.y - s.y) * sin(PI / 3);
    u.y = s.y + (t.x - s.x) * sin(PI / 3) + (t.y - s.y) * cos(PI / 3);

    calcKochCurvePoint(n - 1, p1, s);
    printPoint(s);
    calcKochCurvePoint(n - 1, s, u);
    printPoint(u);
    calcKochCurvePoint(n - 1, u, t);
    printPoint(t);
    calcKochCurvePoint(n - 1, t, p2);
}

void printPoint(Point& p)
{
    std::cout << p.x << " " << p.y << std::endl;
}