#include<bits/stdc++.h>

#define REP(i,s,n) for(int i=s;i<n;i++)
#define rep(i,n) REP(i,0,n)
#define EPS (1e-3)
#define equals(a,b) (fabs((a)-(b))<EPS)

using namespace std;

int d,bc[(1<<10)];
double answer[10];

typedef vector<double> vd;
typedef vector<vd> mat;

vector<double> gauss_jordan(const mat& A,const vd& b){
  int n = A.size();
  mat B(n,vd(n+1));
  for(int i=0;i<n;i++) for(int j=0;j<n;j++) B[i][j] = A[i][j];

  for(int i=0;i<n;i++) B[i][n] = b[i];

  for(int i=0;i<n;i++) {
    int pivot = i;
    for(int j=i;j<n;j++) if( abs(B[j][i]) > abs(B[pivot][i]) ) pivot = j;
    swap(B[i],B[pivot]);
    if( abs(B[i][i]) < EPS ) return vd();
    
    for(int j=i+1;j<=n;j++) B[i][j] /= B[i][i];
    for(int j=0;j<n;j++) {
      if( i != j )  {
        for(int k=i+1;k<=n;k++) B[j][k] -= B[j][i] * B[i][k];
      }
    }
  }
  vd x(n);
  for(int i=0;i<n;i++) x[i] = B[i][n];
  return x;
}

inline double f(const vector<double> &coef,double x){
  double ret = coef.back();
  double multi = x;
  for(int i=(int)coef.size()-2;i>=0;i--) { ret += coef[i] * x; x = x * multi; }
  return ret;
}

bool isValid(const vector<double> &x,const vector<double> &v,int sp,int bitmask,const int wrong){
  if( bc[bitmask] >= d+1 ) {
    vector<vector<double> > A;
    vector<double> b;
    rep(i,d+3) {
      if( (bitmask>>i) & 1 ) {
        b.push_back(v[i]);
        vector<double> tmp;
        tmp.push_back(1);
        rep(j,d) tmp.push_back(tmp.back()*x[i]);
        reverse(tmp.begin(),tmp.end());
        A.push_back(tmp);
      }
    }
    vector<double> coef = gauss_jordan(A,b);

    if( coef.empty() ) return false; // 
    rep(i,d+3) if( i != wrong && !equals(answer[i],f(coef,i)) ) return false;
    return true;
  }
  REP(i,sp,(int)x.size()) if( !isValid(x,v,i+1,bitmask|(1<<i),wrong) ) return false;
  return true;
}

int main(){
  rep(i,(1<<10)) bc[i] = __builtin_popcount(i);
  while( cin >> d, d ){
    rep(i,d+3) cin >> answer[i];
    rep(wrong,d+3){
      vector<double> x,v;
      rep(i,d+3) if( i != wrong ) x.push_back(i), v.push_back(answer[i]);
      if( isValid(x,v,0,0,wrong) ) {
        printf("%d\n",wrong);
        break;
      }
    }
  }
  return 0;
}