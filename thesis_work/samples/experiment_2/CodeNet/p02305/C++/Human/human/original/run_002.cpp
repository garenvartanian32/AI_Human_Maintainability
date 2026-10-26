#include <bits/stdc++.h>
#define rep(i,n) for(int i=0;(i)<(n);++(i))
using namespace std;

// Description: ????????????
// Verifyed: various problem
using namespace placeholders;
using R = long double;
const R EPS = 1e-9L; // [-1000,1000]->EPS=1e-8 [-10000,10000]->EPS=1e-7
inline int sgn(const R& r) {return (r > EPS) - (r < -EPS);}
inline R sq(R x) {return sqrt(max(x, 0.0L));}

const R INF = 1E40L;
const R PI = acos(-1.0L);
using P = complex<R>;
using L = struct {P s, t;};
using VP = vector<P>;
using C = struct {P c; R r;};

#define at(a,i) (a[(i + a.size()) % a.size()])

auto& operator >> (istream& is, P& p) { R x, y; is >> x >> y, p = P(x, y); return is;}
auto& operator << (ostream& os, P& p) { os << real(p) << " " << imag(p); return os;}

namespace std {
bool operator <  (const P& a, const P& b) { return sgn(real(a - b)) ? real(a - b) < 0 : sgn(imag(a - b)) < 0;}
bool operator == (const P& a, const P& b) { return sgn(real(a - b)) == 0 && sgn(imag(a - b)) == 0;}
}

inline R dot(P o, P a, P b) {return real(conj(a - o) * (b - o));}
inline R det(P o, P a, P b) {return imag(conj(a - o) * (b - o));}
inline P vec(L l) {return l.t - l.s;}
auto sdot = bind(sgn, bind(dot, _1, _2, _3));
auto sdet = bind(sgn, bind(det, _1, _2, _3));

//projection verify AOJ CGL_1_A
P proj(L l, P p) { R u = real((p - l.s) / vec(l)); return (1 - u) * l.s + u * l.t;}

// vertical parallel
// verified: AOJ CGL_2_A
bool vertical(L a, L b) {return sdot(0, vec(a), vec(b)) == 0;}
bool parallel(L a, L b) {return sdet(0, vec(a), vec(b)) == 0;}
bool eql(L a, L b) { return parallel(a, b) and sdet(a.s, a.t, b.s) == 0;}

// crossing determination
// verified: AOJ CGL_2_B
bool iss(L a, L b) {
	int sa = sdet(a.s, a.t, b.s) * sdet(a.s, a.t, b.t);
	int sb = sdet(b.s, b.t, a.s) * sdet(b.s, b.t, a.t);
	return max(sa, sb) < 0;
}

// crossing point
// verified: AOJ CGL_2_C
P cross(L a, L b) {
	R u = det(a.s, b.s, b.t) / det(0, vec(a), vec(b));
	return (1 - u) * a.s + u * a.t;
}

// distance
// verified: AOJ CGL_2_D
R dsp(L l, P p) {
	P h = proj(l, p);
	if (sdot(l.s, l.t, p) <= 0) h = l.s;
	if (sdot(l.t, l.s, p) <= 0) h = l.t;
	return abs(p - h);
}

R dss(L a, L b) {return iss(a, b) ? 0 : min({dsp(a, b.s), dsp(a, b.t), dsp(b, a.s), dsp(b, a.t)});}

// Polygon

// area
// verified: AOJ 1100 CGL_3_A
R area(const VP& pol) {
	R sum = 0.0;
	rep(i, pol.size()) sum += det(0, at(pol, i), at(pol, i + 1));
	return abs(sum / 2.0L);
}

// convex_polygon determination
// verified: CGL_3_B
bool is_convex(const VP& pol) {
	rep(i, pol.size())if (sdet(at(pol, i), at(pol, i + 1), at(pol, i + 2)) < 0) return false;
	return true;
}

// polygon realation determination  in  2 on 1 out 0???(possible non-convex)
// verified: AOJ CGL_3-C
int in_polygon(const VP& pol, const P& p) {
	int res = 0;
	auto simag = [](const P & p) {return sgn(imag(p));};
	rep(i, pol.size()) {
		P a = at(pol, i), b = at(pol, i + 1);
		if (sdet(p, a, b) == 0 and sdot(p, a, b) <= 0) return 1;
		bool f = simag(p - a) >= 0, s = simag(p - b) < 0;
		if (simag(b - a)*sdet(a, b, p) == 1 and f == s) res += (2 * f - 1);
	}
	return res ? 2 : 0;
}

// polygon realation determination???(possible non-convex)
// verified: not AOJ 2514
bool in_polygon(const VP& pol, const L& l) {
	VP check = {l.s, l.t};
	rep(i, pol.size()) {
		L edge = {at(pol, i), at(pol, i + 1)};
		if (iss(l, edge)) check.emplace_back(cross(l, edge));
	}

	auto cmp_x = [](P a, P b)->bool{
		int sr = sgn(real(a - b)), si = sgn(imag(a - b));
		return sr ? sr < 0 : si < 0;
	};

	sort(begin(check), end(check), cmp_x);
	rep(i, check.size() - 1) {
		P m = (at(check, i) + at(check, i + 1)) / 2.0L;
		if (in_polygon(pol, m) == false) return false;
	}
	return true;
}

// convex_cut
// verified: AOJ CGL_4_C
VP convex_cut(const VP& pol, const L& l) {
	VP res;
	rep(i, pol.size()) {
		P a = at(pol, i), b = at(pol, i + 1);
		int da = sdet(l.s, l.t, a), db = sdet(l.s, l.t, b);
		if (da >= 0) res.emplace_back(a);
		if (da * db < 0) res.emplace_back(cross({a, b}, l));
	}
	return res;
}

// farthest point pair Verify AOJ CGL_4_B
R fpp(VP pol) {
	int n = pol.size(), i = 0, j = 0;
	if (n <= 2) return abs(pol[0] - pol[1]);
	R res = 0.0;

	auto cmp_x = [](P a, P b)->bool{
		int sr = sgn(real(a - b)), si = sgn(imag(a - b));
		return sr ? sr < 0 : si < 0;
	};

	rep(k, n) {
		if (!cmp_x(pol[i], pol[k]))i = k;
		if (cmp_x(pol[j], pol[k]))j = k;
	}

	int si = i, sj = j;
	while (i != sj || j != si) {
		res = max(res, abs(pol[i] - pol[j]));
		if (sdet(0, vec(L{at(pol, i), at(pol, i + 1)}), vec(L{at(pol, j), at(pol, j + 1)})) < 0)
			i = (i + 1) % n;
		else
			j = (j + 1) % n;
	}
	return res;
}

// closest point pair Verify AOJ CGL_5_A
R iscp(VP a, int flag = 1) {
	const int n = a.size(), m = n / 2;
	if (n <= 1) return INF;

	auto cmp_x = [](P a, P b)->bool{
		int sr = sgn(real(a - b)), si = sgn(imag(a - b));
		return sr ? sr < 0 : si < 0;
	};

	if (flag) sort(begin(a), end(a), cmp_x);

	VP b(begin(a), begin(a) + m), c(begin(a) + m, end(a));
	R x = real(a[m]), d = min(iscp(b, 0), iscp(c, 0));


	auto cmp_y = [](P a, P b)->bool{
		int sr = sgn(real(a - b)), si = sgn(imag(a - b));
		return si ? si < 0 : sr < 0;
	};

	sort(begin(a), end(a), cmp_y);
	deque<P> e;

	for (auto &p : a) {
		if (abs(real(p) - x) >= d) continue;

		for (auto &q : e) {
			if (imag(p - q) >= d) break;
			d = min(d, abs(p - q));
		}
		e.push_front(p);
	}
	return d;
}

// Circle // verified: AOJ 1183
enum RCC {OUT = 2, ON_OUT = 1, ISC = 0, ON_IN = -1, IN = -2};
int rcc(C a, C b) { R d = abs(a.c - b.c); return sgn(d - a.r - b.r) + sgn(d - abs(a.r - b.r));}

// circle crossing determination
bool iisc(C c, P p, int end = 0) {return sgn(abs(p - c.c) - c.r) <= -end;}
bool ics(C c, L s, int end = 0) {return sgn(dsp(s, c.c) - c.r) <= -end;}
bool icsc(C c, L l, int end = 0) {
	return ics(c, l) and sgn(max(abs(l.s - c.c), abs(l.t - c.c)) - c.r) <= 0;
}

// common area between circles
R area(C a, C b) {
	int r = rcc(a, b);
	if (r >= ON_OUT) return 0.0L;
	if (r <= ON_IN) return min(norm(a.r), norm(b.r)) * PI;
	R d = abs(b.c - a.c), rc = (norm(d) + norm(a.r) - norm(b.r)) / (2.0 * d);
	R t = acos(rc / a.r), p = acos((d - rc) / b.r);
	return norm(a.r) * t + norm(b.r) * p - d * a.r * sin(t);
}

// cross point between circle and line
// verified: AOJ CGL_7_D
P cir(C c, R t) {return c.c + polar(c.r, t);}
VP cross(C c, L l) {
	P h = proj(l, c.c);
	P e = polar(sq(norm(c.r) - norm(h - c.c)), arg(vec(l)));
	return VP{h - e, h + e};
}

// cross point between circles
// verified: AOJ CGL_7_E
VP cross(C a, C b) {
	P d = b.c - a.c;
	P w = (norm(d) + norm(a.r) - norm(b.r)) / (2.0L * norm(d)) * d;
	return cross(a, {a.c + w, a.c + w + 1il * d});
}

// circle tangent
// verified: AOJ CGL_7_F
L tan(C c, P p) {return L{p, p + 1il * (p - c.c)};}

P helper(C c, P d, R r, P j) {
	P tmp = sq(norm(d) - norm(r)) * j;
	P dir = (r + tmp) / norm(d) * d;
	return c.c + c.r * dir;
}

VP contact(C c, P p) {
	VP ret;
	P d = p - c.c;
	for (P j : { -1il, 1il}) ret.emplace_back(helper(c, d, c.r, j));
	sort(begin(ret), end(ret));
	ret.erase(unique(begin(ret), end(ret)), end(ret));
	return ret;
}

// circle tangent
// Verified: AOJ CGL_7_G
VP contact(C a, C b) {
	VP ret;
	P d = b.c - a.c;
	for (int s : { -1, 1}) {
		if (rcc(a, b) >= s) {
			for (P j : { -1i, 1i}) {
				R r = a.r + s * b.r;
				ret.emplace_back(helper(a, d, r, j));
			}
		}
	}
	sort(begin(ret), end(ret));
	ret.erase(unique(begin(ret), end(ret)), end(ret));
	return ret;
}
/*
// common area of circle and polygon
R area(R r, P a, P b) {
	return sgn(abs((a + b) / 2.0l) - r) <= 0 ? det(0, a, b) / 2.0l : norm(r) * arg(b - a) / 2.0l;
}

R area(C c, VP pol) {
	R res = 0;
	rep(i, pol.size()){
		R r = c.r;
		P a = at(pol, i) - c.c , b = at(pol, i + 1) - c.c;
		if (sgn(min({r, abs(a), abs(b), abs(b - a)})) == 0) continue;

		VP isc = cross(C{0.0l, r}, L{a, b});
		if (sdot(a, isc[0], isc[1]) <= 0 and sdot(b, isc[0], isc[1]) <= 0) 
			res += area(r, a, b);
		else 
			res += area(r, a, isc[0]) + area(r, isc[0], isc[1]) + area(r, isc[1], b);
	}
	return res;
}
*/int main(void){
	C a,b;
	cin >> a.c >> a.r >> b.c >> b.r;
	cout << rcc(a,b)+2	<< endl;
	return 0;
}