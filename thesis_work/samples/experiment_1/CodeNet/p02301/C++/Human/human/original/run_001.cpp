#include<bits/stdc++.h>

using namespace std;

#define EPS (1e-10)
#define equals(a,b) (fabs((a)-(b))<EPS)

inline int sign(const double &x){
    if(x>EPS) return 1;
    else if(x<-EPS) return -1;
    return 0;
}

/*   点  */
struct Point{
    double x,y;

    Point(double x=0,double y=0):x(x),y(y){}

    Point operator + (Point p) { return Point(x+p.x,y+p.y); }
    Point operator - (Point p) { return Point(x-p.x,y-p.y); }
    Point operator * (double k) { return Point(k*x,k*y); }
    Point operator / (double k) { return Point(x/k,y/k); }

    //向量的大小
    double abs(){ return sqrt(norm()); }
    double norm() { return x*x+y*y; }

    bool operator < (const Point & p) const{
        return x!=p.x?x<p.x:y<p.y;
    }

    bool operator == (const Point & p) const {
        return fabs(x-p.x)<EPS&&fabs(y-p.y)<EPS;
    }
};

//两点间的距离
double getDistance(Point a,Point b){
    return (a-b).abs();
}

/*   向量  */
typedef Point Vector;

//向量a和b的内积
double dot(Vector a,Vector b){
    return a.x*b.x+a.y*b.y;
}
//向量a和b的外积
double cross(Vector a,Vector b){
    return a.x*b.y-a.y*b.x;
}

/*   线段  */
struct Segment{
    Point p1,p2;
};

//点p在线段s上的投影
Point project(Segment s,Point p){
    Vector base = s.p2-s.p1;
    double r=dot(p-s.p1,base)/base.norm();
    return s.p1+base*r;
}

Point reflect(Segment s,Point p){
    return p+(project(s,p)-p)*2.0;
}

/*   直线  */
typedef Segment Line;

//点到直线的距离
double getDistanceLP(Line l,Point p){
    return abs(cross(l.p2-l.p1,p-l.p1)/(l.p2-l.p1).abs());
}

//点到线段的距离
double getDistanceSP(Segment s,Point p){
    if(dot(s.p2-s.p1,p-s.p1)<0.0) return (p-s.p1).abs();
    if(dot(s.p1-s.p2,p-s.p2)<0.0) return (p-s.p2).abs();
    return getDistanceLP(s,p);
}

//判断p2与向量p1-p0的位置关系

const int COUNTER_CLOCKWISE = -1;   //逆时针
const int CLOCKWISE = 1;            //顺时针
const int ON_SEGMENT = 0;           //在线段上
const int ONLINE_BACK = 2;          //p2在向量p1-p0的反方向延长线上
const int ONLINE_FRONT = -2;        //p2在向量p1-p0的正方向延长线上

int ccw(Point p0,Point p1,Point p2){
    Vector a=p1-p0;
    Vector b=p2-p0;
    if(cross(a,b)>EPS) return COUNTER_CLOCKWISE;
    if(cross(a,b)<-EPS) return CLOCKWISE;
    if(dot(a,b)<-EPS) return ONLINE_BACK;
    if(a.norm()<b.norm()) return ONLINE_FRONT;
    return ON_SEGMENT;
}

//判断线段p1p2与线段p3p4是否相交
bool intersect(Point p1,Point p2,Point p3,Point p4){
    return ( ccw(p1,p2,p3)*ccw(p1,p2,p4)<=0 &&
             ccw(p3,p4,p1)*ccw(p3,p4,p2)<=0 );
}

bool intersect(Segment s1,Segment s2){
    return intersect(s1.p1,s1.p2,s2.p1,s2.p2);
}

//线段与线段的距离
double getDistance(Segment s1,Segment s2){
    if(intersect(s1,s2)) return 0.0;
    return min(min(getDistanceSP(s1,s2.p1),getDistanceSP(s1,s2.p2)),
               min(getDistanceSP(s2,s1.p1),getDistanceSP(s2,s1.p2)));
}

//两个线段的交点
Point getCrossPoint(Segment s1,Segment s2){
    Vector base=s2.p2-s2.p1;
    double d1=abs(cross(base,s1.p1-s2.p1));
    double d2=abs(cross(base,s1.p2-s2.p1));
    double t=d1/(d1+d2);
    return s1.p1+(s1.p2-s1.p1)*t;
}

/* 直线正交
 * 判断向量a，b是否正交 =》a，b的内积为0
 */
bool isOrthogonal(Vector a,Vector b){
    return equals(dot(a,b),0.0);
}

bool isOrthogonal(Point a1,Point a2,Point b1,Point b2){
    return isOrthogonal(a1-a2,b1-b2);
}

/* 直线平行
 * 判断向量a，b是否平行 =》a，b的内积为0
 */
bool isParallel(Vector a,Vector b){
    return equals(cross(a,b),0.0);
}

bool isParallel(Point a1,Point a2,Point b1,Point b2){
    return isParallel(a1-a2,b1-b2);
}

struct Circle{
    Point c;
    double r;
    Circle(Point c = Point() ,double r = 0.0 ):c(c),r(r){}
};

/* 圆与直线的交点
 * 交点为一个的时候返回两个相同的点
 * 没有交点则退出
 */

bool intersect(Circle c,Line l){
    return sign(getDistanceLP(l,c.c)-c.r)<=0;
}

pair<Point,Point> getCrossPoints(Circle c,Line l){
    assert(intersect(c,l));         //没有交点
    Point pr=project(l,c.c);
    Vector e=(l.p2-l.p1)/(l.p2-l.p1).abs();
    double base=sqrt(c.r*c.r-(pr-c.c).norm());
    return make_pair(pr+e*base,pr-e*base);
}


//圆与圆的交点

bool intersect(Circle c1,Circle c2){
    return sign(getDistance(c1.c,c2.c)-(c1.r+c2.r))<=0;
}

double arg(Vector p){
    return atan2(p.y,p.x);// double atan2(double y,double x) 返回的是原点至点(x,y)的方位角，即与 x 轴的夹角。返回值的单位为弧度，取值范围为(-PI,PI]
}

Vector polar(double r,double a){
    return Point(cos(a)*r,sin(a)*r);
}

pair<Point,Point> getCrossPoints(Circle c1,Circle c2){
    assert(intersect(c1,c2));
    double d=(c1.c-c2.c).abs();
    double a=acos((c1.r*c1.r+d*d-c2.r*c2.r)/(2*c1.r*d));
    double t=arg(c2.c-c1.c);
    return make_pair(c1.c+polar(c1.r,t+a),c1.c+polar(c1.r,t-a));
}

/*   多边形  */
typedef vector<Point> Polygon;

//点的****内包*****(判断点与多边形的关系)
//多边形内返回2
//多边形上返回1
//多边形外返回0
int contains(Polygon g,Point p){
    int n=g.size();
    bool x=false;
    for(int i=0;i<n;i++){
        Point a=g[i]-p,b=g[(i+1)%n]-p;
        if(abs(cross(a,b))<EPS&&dot(a,b)<EPS) return 1;
        if(a.y>b.y) swap(a,b);
        if(a.y<EPS&&EPS<b.y&&cross(a,b)>EPS) x=!x;
    }
    return (x?2:0);
}

/* *******凸包*****(包含点集合p中所有点的最小凸多边形)
 * 输出凸多边形最下端最左侧的顶点为起点,按逆时针方向依次输出坐标。
 * 安德鲁算法 O(n*logn)
 */

Polygon andrewscanf(Polygon s){
    int n=s.size();
    if(n<3){
        return s;
    }
    sort(s.begin(),s.end());    //x从小到大，x相同的y从小到大
    Polygon u,l;
    u.push_back(s[0]);
    u.push_back(s[1]);

    l.push_back(s[n-1]);
    l.push_back(s[n-2]);
    //构建凸包上部
    for(int i=2;i<n;i++){
        for(int j=u.size();j>=2&&(ccw(u[j-2],u[j-1],s[i])==COUNTER_CLOCKWISE);j--){
            u.pop_back();
        }
        u.push_back(s[i]);
    }
    //构建凸包下部
    for(int i=n-3;i>=0;i--){
        for(int j=l.size();j>=2&&(ccw(l[j-2],l[j-1],s[i])==COUNTER_CLOCKWISE);j--){
            l.pop_back();
        }
        l.push_back(s[i]);
    }
    //按顺序生成凸包点的序列
    reverse(l.begin(),l.end());
    for(int i=u.size()-2;i>=1;i--){
        l.push_back(u[i]);
    }
    return l;
}

double RC(Polygon v){
    double res=-1;
    int n=v.size();
    for(int i=0,k=0;i<n;i++){
        while(sign((v[i]-v[k]).norm()-(v[i]-v[(k+1)%n]).norm())==-1) k=(k+1)%n;
        res=max(res,(v[i]-v[k]).abs());
    }
    return res;
}

int main(){
    int n;
    scanf("%d",&n);
    Polygon v(n);
    for(int i=0;i<n;i++){
        scanf("%lf%lf",&v[i].x,&v[i].y);
    }
    printf("%.10f\n",RC(v));
    return 0;
}

