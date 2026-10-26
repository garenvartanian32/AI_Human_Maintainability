#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
int main(){
  int n, k;
  cin >> n;
  while(n--){
    vector<int> p, q;
    int u=0, v=0;
    cin >> k;
    for(int i=0; i<8; i++){
      p.push_back(k%10);
      k/=10;
    }
    sort(p.begin(),p.end());
    q.resize(8);
    for(int i=0; i<8; i++)
      q[7-i] = p[i];
    int sep = 1;
    for(int i=0; i<8; i++){
      u+= sep*p[i];
      v+= sep*q[i];
      sep*=10;
    }
    cout << u-v << endl;
  }
  return 0;
}