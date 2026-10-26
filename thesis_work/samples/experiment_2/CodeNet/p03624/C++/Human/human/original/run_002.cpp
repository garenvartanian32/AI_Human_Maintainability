#include <algorithm>
#include <bitset>
#include <cctype>
#include <cmath>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <ctime>
#include <deque>
#include <functional>
#include <iomanip>
#include <iostream>
#include <list>
#include <map>
#include <numeric>
#include <set>
#include <sstream>
#include <stack>
#include <string>
#include <utility>
#include <vector>

using namespace std;

#define FOR(i, first, last) for (int i = (first); i < (last); ++i)
#define REP(i, n) FOR(i, 0, n)
#define CLR(a) memset((a), 0, sizeof(a))
#define DUMP(x) std::cerr << #x << " = " << (x) << std::endl;

inline int to_int(string s) {
  int v;
  istringstream sin(s);
  sin >> v;
  return v;
}
template <class T> inline string to_string(T x) {
  ostringstream sout;
  sout << x;
  return sout.str();
}

typedef unsigned long long ull;
typedef unsigned long ul;

int main() {
  set<char> al;
  for(char c='a'; c<='z'; c++) al.insert(c);

  string s;
  cin>>s;

  set<char> ss;
  ss.insert(s.begin(), s.end());

  if(al.size()==ss.size()) cout <<"None"<<endl;
  else {
    for(auto c='a'; c<='z'; c++) {
      if(ss.find(c)==ss.end()) {
        cout<<c<<endl;
        break;
      }
    }
  }

  return 0;
}
