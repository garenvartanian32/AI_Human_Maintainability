#include <stdio.h>
#include <bits/stdc++.h>

using namespace std;
#define ll long long
#define ld long double
#define  P pair<ll,ll>
#define FOR(i,n,m) for(ll i=n; i<(ll)m;i++)
#define FORr(i,m,n) for(ll i=n; i>=(ll)m; i--)
#define FORm(i,m) for(auto i=m.begin();i!=m.end();i++)
#define VEC2(x,a,b) vector<vector<ll>> x = vector<vector<ll>>(a,vector<ll>(b,0))
#define sortAl(v) sort(v.begin(),v.end())
#define sortAlr(v) sort(v.begin(),v.end()); reverse(v.begin(),v.end())
#define cinv(v,n,m) FOR(i,n,m) cin>>v.at(i)
#define coutv(v,n,m) FOR(i,n,m) cout<<v.at(i) <<" "; cout<<endl
#define cout(n) cout<<fixed<<setprecision(n)

vector<string> v;
vector<string> list_ans;
ll dir_j[8] = {0,1,1,1,0,-1,-1,-1};
ll dir_i[8] = {1,1,0,-1,-1,-1,0,1};

void push(ll i, ll j){
  ll h = v.size();
  ll w = v[0].size();
  FOR(p,0,8){
    ll tmp_i = i;
    ll tmp_j = j;
    string s;
    s.push_back(v[tmp_i][tmp_j]);
    tmp_i += dir_i[p];
    tmp_j += dir_j[p];
    tmp_i += h;
    tmp_j += w;
    tmp_i %= h;
    tmp_j %= w;
    while(!(tmp_i == i && tmp_j == j)){
//      cout<<"t_i = "<<tmp_i<<" t_j = "<<tmp_j<<endl;
      s.push_back(v[tmp_i][tmp_j]);
      tmp_i += dir_i[p];
      tmp_j += dir_j[p];
      tmp_i += h;
      tmp_j += w;
      tmp_i %= h;
      tmp_j %= w;
    }
    list_ans.push_back(s);
  }
}

string ch_res(string a, string b){
  if(a.size()<b.size()){
    return b;
  }
  return a;
}

int main(){
    cin.tie(0);
    ios::sync_with_stdio(false);
    ll h,w;
    cin>>h>>w;
    while(h!=0){
      v = vector<string>(0,"");
      list_ans = vector<string>(0,"");
      FOR(i,0,h){
        string s;
        cin>>s;
        v.push_back(s);
      }

      FOR(i,0,h){
        FOR(j,0,w){
//          cout<<"i = "<<i<<" j = "<<j<<endl;
          push(i,j);
        }
      }

      sortAl(list_ans);
      string res;
      FOR(i,1,list_ans.size()){
        string a = list_ans[i-1];
        string b = list_ans[i];
        string tmp_a;
        string tmp_b;
        FOR(j,0,a.size()){
          tmp_a.push_back(a[j]);
          tmp_b.push_back(b[j]);
          if(tmp_a == tmp_b) res = ch_res(res, tmp_a);
        }
      }

      if(res.size() == 1) cout<<0<<endl;
      else cout<<res<<endl;

      cin>>h>>w;
    }
}

