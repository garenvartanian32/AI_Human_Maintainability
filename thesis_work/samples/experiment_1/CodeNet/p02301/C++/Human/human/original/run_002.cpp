#include <stdio.h>
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <cctype>
#include <string>
#include <cstring>
#include <algorithm>
#include <stack>
#include <queue>
#include <set>
#include <map>
#include <ctime>
#include <vector>
#include <fstream>
#include <list>
#include <iomanip>
#include <numeric>
using namespace std;
#define ll long long
#define ull unsigned long long
#define db double
#define REP(i, lim) for(int i=0;i<lim;++i)
#define REPP(i, lim) for(int i=1;i<=lim;++i)
#define DEC(i, lim) for(int i=lim;i>=1;--i)
#define FOR(i,l,r)  for(int i=l;i<r;++i)
#define deBug cout<<"==================================="<<endl;
#define clr(s) memset(s, 0, sizeof(s))
#define lowclr(s) memset(s, -1, sizeof(s))
const int MAXN = 1000055;
const int inf = 0x3f3f3f3f;
const double pi = acos(-1.0);
const db eps = 1e-9;

inline int sgn(db x) { return x<-eps ? -1 : x>eps; }
inline db sqr(db x) { return x*x; }

#define cross(p1, p2, p3) ((p2.x-p1.x)*(p3.y-p1.y)-(p3.x-p1.x)*(p2.y-p1.y))
#define crossOp(p1, p2, p3) sgn(cross(p1, p2, p3))

struct P {
    // information
    P() {}
    P(db _x, db _y) : x(_x), y(_y) {}
    db x, y;
    // operation
    P operator + (P p)   const{ return P(x+p.x, y+p.y); }
    P operator - (P p)   const{ return P(x-p.x, y-p.y); }
    P operator * (db d)  const{ return P(x*d, y*d); }
    P operator / (db d)  const{ return P(x/d, y/d); }
    bool operator<(P p) const {
        int c = sgn(x-p.x);
        if (c) return c == -1;
        return sgn(y-p.y)==-1;
    }
    db dot(P p) { return x * p.x + y * p.y; } // 点积
    db det(P p) { return x * p.y - y * p.x; } // 叉积

    // other
    void input() { scanf("%lf%lf", &x, &y); }
    void print() { printf("(%lf, %lf)\n", x, y); }
    db disTo(P p) { return sqrt(sqr(x-p.x) + sqr(y-p.y)); }
    db abs() { return sqrt(x*x + y*y); }
    db abs2(){ return x*x + y*y; }
    db getw(){ return atan2(y,x); }
    int getP() const{ return sgn(y)==1||(sgn(y)==0&&sgn(x)>=0); }
};
int cmpAngle (P a, P b){
    if(a.getP()!=b.getP())  return a.getP()<b.getP();
    else                    return sgn(a.det(b)) > 0;
}
bool chkLL(P p1, P p2, P q1, P q2) {
    db a1 = cross(q1, q2, p1), a2 = -cross(q1, q2, p2);
    return sgn(a1+a2) != 0;
}
P getLL(P p1, P p2, P q1, P q2) {
    db a1 = cross(q1, q2, p1), a2 = -cross(q1, q2, p2);
    return (p1 * a2 + p2 * a1) / (a1 + a2);
}
bool intersect(db l1, db r1, db l2, db r2) {
    if(l1>r1) swap(l1, r1); if(l2>r2) swap(l2, r2);
    return !( sgn(r1-l2) == -1 || sgn(r2-l1) == -1 );
}
bool chkSS(P p1, P p2, P q1, P q2) {
    return intersect(p1.x, p2.x, q1.x, q2.x) && intersect(p1.y, p2.y, q1.y, q2.y) &&
           crossOp(p1, p2, q1) * crossOp(p1, p2, q2) <= 0 &&
           crossOp(q1, q2, p1) * crossOp(q1, q2, p2) <=0;
}
bool chkSS_strict(P p1, P p2, P q1, P q2) {
    return crossOp(p1, p2, q1) * crossOp(p1, p2, q2) < 0 &&
           crossOp(q1, q2, p1) * crossOp(q1, q2, p2) < 0;
}
bool inMiddle(db a, db m, db b) { return sgn(a-m)==0 || sgn(b-m)==0 || (a<m != b<m); }
bool inMiddle(P a, P m, P b) { return inMiddle(a.x, m.x, b.x) && inMiddle(a.y, m.y, b.y); }
bool onSeg(P p1, P p2, P q) { return crossOp(p1, p2, q) == 0 && inMiddle(p1, q, p2); }
bool onSeg_strict(P p1, P p2, P q) {
    return crossOp(p1, p2, q) == 0 && sgn((q-p1).dot(p1-p2)) * sgn((q-p2).dot(p1-p2)) < 0;
}
P getProj(P a, P b, P p) { return a + (b-a) * ( (b-a).dot(p-a) / sqr(a.disTo(b)));}
P getReflect(P a, P b, P p) { return getProj(a, b, p) * 2 - p; }
db nearest(P p1, P p2, P q) {
    P h = getProj(p1, p2, q);
    if(inMiddle(p1, h, p2)) return q.disTo(h);
    return min(p1.disTo(q), p2.disTo(q));
}
db disSS(P p1, P p2, P q1, P q2) {
    if(chkSS(p1, p2, q1, q2)) return 0;
    return min(min(nearest(p1,p2,q1), nearest(p1,p2,q2)), min(nearest(q1,q2,p1),nearest(q1,q2,p2)));
}

int contain(vector<P>A,P q){ // 2 内部 1 边界 0 外部
    int pd=0; A.push_back(A[0]);
    for (int i=1;i<A.size();i++){
        P u=A[i-1],v=A[i];
        if (onSeg(u,v,q)) return 1; if (sgn(u.y-v.y)>0) swap(u,v);
        if (sgn(u.y-q.y)>=0||sgn(v.y-q.y)<0) continue;
        if (sgn((u-v).det(q-v))<0) pd^=1;
    }
    return pd<<1;
}
vector<P> convexHull (vector<P> ps) {
    int n = ps.size(); if(n<=1) return ps;
    sort(ps.begin(), ps.end());
    vector<P> qs(n*2); int k = 0;
    for(int i=0;i<n;qs[k++]=ps[i++])
        while(k>1 && crossOp(qs[k-2], qs[k-1], ps[i])<=0) --k;
    for(int i=n-2,t=k;i>=0;qs[k++]=ps[i--])
        while(k>t && crossOp(qs[k-2], qs[k-1], ps[i])<=0) --k;
    qs.resize(k-1);
    return qs;
}
vector<P> convexHull_noStrict (vector<P> ps) {
    int n = ps.size(); if(n<=1) return ps;
    sort(ps.begin(), ps.end());
    vector<P> qs(n*2); int k = 0;
    for(int i=0;i<n;qs[k++]=ps[i++])
        while(k>1 && crossOp(qs[k-2], qs[k-1], ps[i])<0) --k;
    for(int i=n-2,t=k;i>=0;qs[k++]=ps[i--])
        while(k>t && crossOp(qs[k-2], qs[k-1], ps[i])<0) --k;
    qs.resize(k-1);
    return qs;
}
int chkConvex(vector<P> ps){
    int n = ps.size();
    ps.push_back(ps[0]); ps.push_back(ps[1]);
    for(int i=0;i<n;++i) if(sgn((ps[i+1]-ps[i]).det(ps[i+2]-ps[i]))==-1) return 0;
    return 1;
}
db convexDiameter(vector<P> ps) {
    int n = ps.size(); if(n<=1) return 0;
    int is = 0, js = 0;
    REPP(k, n) is = ps[k]<ps[is] ? k:is, js = ps[js] < ps[k] ? k : js;
    int i = is, j = js;
    db ret = ps[i].disTo(ps[j]);
    do{
        if((ps[(i+1)%n] - ps[i]).det(ps[(j+1)%n]-ps[j])>=0)
            (++j) %= n;
        else
            (++i) %= n;
        ret = max(ret, ps[i].disTo(ps[j]));
    } while(i!=is || j!=js);
    return ret;
}


int main()
{
//    freopen("in.txt", "r", stdin);
//    freopen("out.txt", "w", stdout);
//    std::ios::sync_with_stdio(false); cin.tie(0);

    vector<P> poly;
    int n;
    scanf("%d", &n);
    REP(i, n) {
        P tmp; tmp.input();
        poly.push_back(tmp);
    }

    vector<P> conv = convexHull_noStrict(poly);
    db ans = convexDiameter(conv);
    printf("%.12lf\n", ans);

    return 0;
}
