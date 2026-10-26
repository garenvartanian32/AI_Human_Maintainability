#include <iostream>
#include <string>
#include <algorithm>
#include <cstdlib>
using namespace std;
int main(){
  int x1,y1,x2,y2;
  cin >> x1>>y1>>x2>>y2;
  int xx=x1-x2;int yy=y1-y2;
    cout <<x2+yy<<" "<<y2-xx<<" "<<x1+yy<<" "<<y1-xx<<endl;
}
