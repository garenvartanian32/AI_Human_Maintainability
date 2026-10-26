#include <bits/stdc++.h>
using namespace std;
#define rep(i,a,n) for(int i=(a);i<(n);i++)
#define per(i,a,n) for(int i=(n)-1;i>=(a);i--)
#define MP make_pair

typedef double db;

const db EPS = 1e-8;

inline int sign(db a) {
	return a < -EPS ? -1 : a > EPS;
}

inline int cmp(db a, db b){
	return sign(a-b);
}

struct P {
	db x, y;
	P() {
	}
	P(db _x, db _y) :
			x(_x), y(_y) {
	}
	P operator+(P p) {
		return P(x + p.x, y + p.y);
	}
	P operator-(P p)  {
		return P(x - p.x, y - p.y);
	}
	P operator*(db d) {
		return P(x * d, y * d);
	}
	P operator/(db d) {
		return P(x / d, y / d);
	}
	bool operator<(P p) const {
		int c = sign(x - p.x);
		if (c)
			return c == -1;
		return sign(y - p.y) == -1;
	}
	db dot(P p) {
		return x * p.x + y * p.y;
	}
	db det(P p) {
		return x * p.y - y * p.x;
	}
	db distTo(P p) { return (*this-p).abs(); }

	db alpha() {
		return atan2(y, x);
	}
	void read() {
		cin>>x>>y;
	}
	db abs() {
		return sqrt(abs2());
	}
	db abs2() {
		return x * x + y * y;
	}
	P rot90(){
		return P(-y,x);
	}
};

#define cross(p1,p2,p3) ((p2.x-p1.x)*(p3.y-p1.y)-(p3.x-p1.x)*(p2.y-p1.y))
#define crossOp(p1,p2,p3) sign(cross(p1,p2,p3))

P isLL(P p1, P p2, P q1, P q2) {
	db a1 = cross(q1, q2, p1), a2 = -cross(q1, q2, p2);
	return (p1 * a2 + p2 * a1) / (a1 + a2);
}

bool intersect(db l1,db r1,db l2,db r2){
	if(l1>r1) swap(l1,r1); if(l2>r2) swap(l2,r2); 
	return !( cmp(r1,l2) == -1 || cmp(r2,l1) == -1 );
}

bool isSS(P p1, P p2, P q1, P q2){
    return intersect(p1.x,p2.x,q1.x,q2.x) && intersect(p1.y,p2.y,q1.y,q2.y) && 
    crossOp(p1,p2,q1) * crossOp(p1,p2,q2) <= 0 && crossOp(q1,q2,p1)
            * crossOp(q1,q2,p2) <= 0;
}

bool isMiddle(db a, db m, db b) {
    return sign(a - m) == 0 || sign(b - m) == 0 || (a < m != b < m);
}
 
bool isMiddle(P a, P m, P b) {
    return isMiddle(a.x, m.x, b.x) && isMiddle(a.y, m.y, b.y);
}

bool onSeg(P p1, P p2, P q){
	return crossOp(p1,p2,q) == 0 && isMiddle(p1, q, p2);
}

P proj(P p1, P p2, P q) {
    P dir = p2 - p1;
    return p1 + dir * (dir.dot(q - p1) / dir.abs2());
}

P reflect(P p1, P p2, P q){
	return proj(p1,p2,q) * 2 - q;
}

db nearest(P p1,P p2,P q){
	P h = proj(p1,p2,q);
	if(isMiddle(p1,h,p2))
		return q.distTo(h);
	return min(p1.distTo(q),p2.distTo(q));
}

db disSS(P p1, P p2, P q1, P q2){
	if(isSS(p1,p2,q1,q2)) return 0;
	return min(min(nearest(p1,p2,q1),nearest(p1,p2,q2)), min(nearest(q1,q2,p1),nearest(q1,q2,p2)) );
}

db getrad(P p1,P p2){
	return atan2l(p1.det(p2),p1.dot(p2));
}

db incircle(P p1, P p2, P p3){
	db A = p1.distTo(p2);
	db B = p2.distTo(p3);
	db C = p3.distTo(p1);
	return sqrtl(A*B*C/(A+B+C));
}

db area(vector<P> ps){
	db ret = 0; rep(i,0,ps.size()) ret += ps[i].det(ps[(i+1)%ps.size()]); 
	return abs(ret/2);
}

int contain(vector<P> ps, P p){ //2:inside,1:on_seg,0:outside
	int n = ps.size(), ret = 0;	
	rep(i,0,n){
		P u=ps[i],v=ps[(i+1)%n];
		if(onSeg(u,v,p)) return 1;
		if(cmp(u.y,v.y)<=0) swap(u,v);
		if(cmp(p.y,u.y) >0 || cmp(p.y,v.y) <= 0) continue;
		ret ^= crossOp(p,u,v) > 0;
	}
	return ret*2;
}

vector<P> convexHull(vector<P> ps) {
    int n = ps.size(); if(n <= 1) return ps;
    sort(ps.begin(), ps.end());
    vector<P> qs(n * 2); int k = 0;
    for (int i = 0; i < n; qs[k++] = ps[i++]) 
        while (k > 1 && crossOp(qs[k - 2], qs[k - 1], ps[i]) <= 0) --k;
    for (int i = n - 2, t = k; i >= 0; qs[k++] = ps[i--])
       	while (k > t && crossOp(qs[k - 2], qs[k - 1], ps[i]) <= 0) --k;
    qs.resize(k - 1);
    return qs;
}

vector<P> convexHullNonStrict(vector<P> ps) {
    int n = ps.size(); if(n <= 1) return ps;
    sort(ps.begin(), ps.end());
    vector<P> qs(n * 2); int k = 0;
    for (int i = 0; i < n; qs[k++] = ps[i++]) 
        while (k > 1 && crossOp(qs[k - 2], qs[k - 1], ps[i]) < 0) --k;
    for (int i = n - 2, t = k; i >= 0; qs[k++] = ps[i--])
       	while (k > t && crossOp(qs[k - 2], qs[k - 1], ps[i]) < 0) --k;
    qs.resize(k - 1);
    return qs;
}

db convexDiameter(vector<P> ps){
	int n = ps.size(); if(n <= 1) return 0;
	int is = 0, js = 0; rep(k,1,n) is = ps[k]<ps[is]?k:is, js = ps[js] < ps[k]?k:js;
	int i = is, j = js;
	db ret = ps[i].distTo(ps[j]);
	do{
		if((ps[(i+1)%n]-ps[i]).det(ps[(j+1)%n]-ps[j]) >= 0)
			(++j)%=n;
		else
			(++i)%=n;
		ret = max(ret,ps[i].distTo(ps[j]));
	}while(i!=is || j!=js);
	return ret;
}

int main(){
	int n;cin>>n;
	vector<P> ps(n);
	rep(i,0,n) ps[i].read();
	printf("%0.10f\n",convexDiameter(ps));
	return 0;
}