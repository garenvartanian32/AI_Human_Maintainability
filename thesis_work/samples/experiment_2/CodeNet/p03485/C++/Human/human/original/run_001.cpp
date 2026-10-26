#include <bits/stdc++.h>
using namespace std;
 
int main() {
  double a, b, mean_d;
  int mean_i, i;
  cin >> a >> b;
  mean_d = (a+b)/2;
  mean_i = (a+b)/2;
  i = mean_d - mean_i + 0.999;
  cout << mean_i+i << endl;
}