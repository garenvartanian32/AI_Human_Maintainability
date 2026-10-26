#include <bits/stdc++.h>
#define ll long long
#define INF 1000000005
#define MOD 1000000007
#define EPS 1e-10
#define rep(i,n) for(int i=0;i<(int)(n);++i)
#define rrep(i,n) for(int i=(int)(n)-1;i>=0;--i)
#define srep(i,s,t) for(int i=(int)(s);i<(int)(t);++i)
#define each(a,b) for(auto& (a): (b))
#define all(v) (v).begin(),(v).end()
#define len(v) (int)(v).size()
#define zip(v) sort(all(v)),v.erase(unique(all(v)),v.end())
#define cmx(x,y) x=max(x,y)
#define cmn(x,y) x=min(x,y)
#define fi first
#define se second
#define pb push_back
#define show(x) cout<<#x<<" = "<<(x)<<endl
#define spair(p) cout<<#p<<": "<<p.fi<<" "<<p.se<<endl
#define sar(a,n) cout<<#a<<":";rep(pachico,n)cout<<" "<<a[pachico];cout<<endl
#define svec(v) cout<<#v<<":";rep(pachico,v.size())cout<<" "<<v[pachico];cout<<endl
#define svecp(v) cout<<#v<<":";each(pachico,v)cout<<" {"<<pachico.first<<":"<<pachico.second<<"}";cout<<endl
#define sset(s) cout<<#s<<":";each(pachico,s)cout<<" "<<pachico;cout<<endl
#define smap(m) cout<<#m<<":";each(pachico,m)cout<<" {"<<pachico.first<<":"<<pachico.second<<"}";cout<<endl

using namespace std;

typedef pair<int,int> P;
typedef pair<ll,ll> pll;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef vector<ll> vl;
typedef vector<vl> vvl;
typedef vector<double> vd;
typedef vector<P> vp;
typedef vector<string> vs;

const int MAX_N = 100005;

template<typename V> class segtree2 {
private:
    int n,sz;
    vector<V> node, lazy;
    vector<bool> lazyFlag;

public:
    segtree2(vector<V>& v) {
        sz = (int)v.size();
        n = 1;
        while(n < sz){
            n *= 2;
        }
        node.resize(2*n-1, 0);
        lazy.resize(2*n-1, 0);
        lazyFlag.resize(2*n-1,false);
        rep(i,sz){
            node[i+n-1] = v[i];
        }
        for(int i=n-2; i>=0; i--){
            node[i] = node[i*2+1] + node[i*2+2];
        }
    }
    void eval(int k, int l, int r) {
        if(lazyFlag[k]) {
            node[k] = lazy[k]*(r-l);
            if(r - l > 1) {
                lazy[k*2+1] = lazy[k*2+2] = lazy[k];
                lazyFlag[k*2+1] = lazyFlag[k*2+2] = true;
            }
            lazyFlag[k] = false;
        }
    }
    void range(int a, int b, V x, int k=0, int l=0, int r=-1) {
        if(r < 0) r = n;
        eval(k, l, r);
        if(b <= l || r <= a){
            return;
        }
        if(a <= l && r <= b) {
            lazy[k] = x;
            lazyFlag[k] = true;
            eval(k, l, r);
        }
        else {
            range(a, b, x, 2*k+1, l, (l+r)/2);
            range(a, b, x, 2*k+2, (l+r)/2, r);
            node[k] = node[2*k+1] + node[2*k+2];
        }
    }
    V query(int a, int b, int k=0, int l=0, int r=-1) {
        if(r < 0) r = n;
        eval(k, l, r);
        if(b <= l || r <= a){
            return 0;
        }
        if(a <= l && r <= b){
            return node[k];
        }
        V vl = query(a, b, 2*k+1, l, (l+r)/2);
        V vr = query(a, b, 2*k+2, (l+r)/2, r);
        return vl + vr;
    }
    void print()
    {
        rep(i,sz){
            cout << query(i,i+1) << " ";
        }
        cout << endl;
    }
};

int main()
{
    cin.tie(0);
    ios::sync_with_stdio(false);
    int n,q;
    cin >> n >> q;
    vector<int> vec(n,0);
    segtree2<int> sg2(vec);
    rep(i,q){
        int a;
        cin >> a;
        if(a==0){
            int b,c,d;
            cin >> b >> c >> d;
            sg2.range(b,c+1,d);
        }else{
            int b,c;
            cin >> b >> c;
            cout << sg2.query(b,c+1) << "\n";
        }
    }
    return 0;
}

